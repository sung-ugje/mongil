/**
 * 테스트 Client Interceptor
 */
package com.openapi.framework.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.openapi.controller.BaseController;
import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.StringUtil;


public class ClientInterceptor extends BaseInterceptor {

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.preHandle(request, response, handler);

		String requestUrl = StringUtil.trimToEmpty(request.getRequestURL().toString());
		String requestUri = StringUtil.trimToEmpty(request.getRequestURI());
		String queryString = URLDecoder.decode(StringUtil.trimToEmpty(request.getQueryString()), "UTF-8");

		request.setAttribute("requestUrl", requestUrl);
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("queryString", queryString);

		sb.setLength(0);
		sb.append("-------------- ClientInterceptor Request Start --------------" + MgConstants.CRLF);
		sb.append("requestUrl : " + requestUrl + MgConstants.CRLF);
		sb.append("requestUri : " + requestUri + MgConstants.CRLF);
		sb.append("queryString : " + queryString + MgConstants.CRLF);
		sb.append("localeResolver.resolveLocale(request) : " + localeResolver.resolveLocale(request) + MgConstants.CRLF);
		sb.append("-------------- ClientInterceptor Request End --------------" + MgConstants.CRLF);
		
		logger.debug(MgConstants.CRLF + sb.toString());
		
		/*헤더정보 조회*/
		/*Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String headerName = e.nextElement();
			logger.debug("{} : {}", new String[]{headerName}, new String[]{StringUtil.trimToEmpty(request.getHeader(headerName))});
		}*/
		
		/*Ajax통신인지 아닌지 구분*/
		ModelAndView mav = new ModelAndView(BaseController.AFTER_ACTION_VIEW);
		if ("XMLHttpRequest".equalsIgnoreCase(StringUtil.trimToEmpty(request.getHeader("x-requested-with")))) {
			mav.setViewName(BaseController.AFTER_AJAX_ACTION_VIEW);
		}
		
		HttpSession session = request.getSession();
		SessionInfo sessionInfo = sessionFactory.getObject();

		request.setAttribute("sessionInfo", sessionInfo);
		request.setAttribute("loginInfo", sessionInfo.getLoginInfo());
		request.setAttribute("isLogin", sessionInfo.isLogin());
		request.setAttribute("locale", localeResolver.resolveLocale(request));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		super.postHandle(request, response, handler, mav);
	}
}
