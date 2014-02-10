package com.openapi.framework.interceptor;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.openapi.controller.BaseController;
import com.openapi.framework.component.AuthCheckComponent;
import com.openapi.framework.component.ParameterCheckComponent;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.StringUtil;
import com.openapi.service.token.TokenService;

public class OpenApiInterceptor extends BaseInterceptor {
	
	@Autowired private AuthCheckComponent authCheckComponent;
	@Autowired private ParameterCheckComponent parameterCheckComponent;
	@Autowired private TokenService tokenService;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		super.preHandle(request, response, handler);
		
		String requestUrl = StringUtil.trimToEmpty((String) request.getAttribute("requestUrl"));
		String requestUri = StringUtil.trimToEmpty((String) request.getAttribute("requestUri"));
		String queryString = StringUtil.trimToEmpty((String) request.getAttribute("queryString"));
		
		sb.setLength(0);
		sb.append("--------------- OpenApiInterceptor Request Start ---------------" + MgConstants.CRLF);
		
		sb.append("requestUrl : " + requestUrl + MgConstants.CRLF);
		sb.append("requestUri : " + requestUri + MgConstants.CRLF);
		sb.append("queryString : " + queryString + MgConstants.CRLF);
		sb.append("localeResolver.resolveLocale(request) : " + localeResolver.resolveLocale(request) + MgConstants.CRLF);
		
		sb.append("--------------- OpenApiInterceptor Request End ---------------" + MgConstants.CRLF);
		
		logger.info(MgConstants.CRLF + sb.toString());
		
		/**
		 * 헤더정보 조회
		 */
		sb.setLength(0);
		sb.append("-------------------------- Request Header Start ----------------------------" + MgConstants.CRLF);
		
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String headerName = StringUtil.trimToEmpty(e.nextElement());
			sb.append(headerName + " : " + StringUtil.trimToEmpty(request.getHeader(headerName)) + MgConstants.CRLF);
			
			/*Accept 정보 셋팅*/
			if ("ACCEPT".equals(StringUtil.upperCase(headerName))) {
				request.setAttribute("ACCEPT", StringUtil.trimToEmpty(request.getHeader(headerName)));
			}
		}
		
		sb.append("-------------------------- Request Header End ----------------------------" + MgConstants.CRLF);
		
		//logger.info(OAConstants.CRLF + sb.toString());
		
		HttpSession session = request.getSession();
		
		/**
		 * LOCALE
		 */
		if ((Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) == null) {
			session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, Locale.KOREAN);
		}
		
		Locale locale = (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		request.setAttribute("locale", locale);
		
		//logger.debug("현재 설정된 언어 : {}", locale.getLanguage());
		
		/**
		 * Ajax통신인지 아닌지 구분
		 */
		ModelAndView mav = new ModelAndView(BaseController.AFTER_ACTION_VIEW);
		if ("XMLHttpRequest".equalsIgnoreCase(StringUtil.trimToEmpty(request.getHeader("x-requested-with")))) {
			mav.setViewName(BaseController.AFTER_AJAX_ACTION_VIEW);
		}
		
		/**
		 * 체크 컴포넌트 실행
		 */
		String[] notCheckUrl = ArrayUtils.toArray(
			"/api/error/", "/api/manage/", "/api/user"
		);
		
		int i = 0;
		if (notCheckUrl != null && notCheckUrl.length > 0) {
			String reqUri = StringUtil.trimToEmpty(request.getRequestURI());
			
			for (String url : notCheckUrl) {
				if (reqUri.startsWith(url)) {
					i++;
					break;
				}
			}
		}
		
		if (i == 0) {
			/*API 인증(Accept, Token유무, 인증유무) 체크*/
			if (!authCheckComponent.checkToken(request, response)) {
				return false;
			}
			
			/*파라미터 체크*/
			if (!parameterCheckComponent.checkParams(request, response)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		super.postHandle(request, response, handler, mav);
		
		/*request.setAttribute("sessionInfo", sessionFactory.getObject());
		request.setAttribute("loginInfo", sessionFactory.getObject().getLoginInfo());
		request.setAttribute("isLogin", sessionFactory.getObject().isLogin());
		request.setAttribute("locale", sessionFactory.getObject().getLocale());*/
		
		/*
		 * 캐쉬정보소멸
		 */
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if ("HTTP/1.1".equalsIgnoreCase(request.getProtocol())) {
			response.setHeader("Cache-Control", "no-cache");
		}
	}
}
