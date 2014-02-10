/**
 * API Key 인증체크
 */
package com.openapi.framework.component;

import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openapi.domain.session.LoginInfo;
import com.openapi.domain.token.TokenBase;
import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.DateUtils;
import com.openapi.framework.utils.StringUtil;
import com.openapi.service.token.TokenService;


@Component
public class AuthCheckComponent extends BaseComponent {
	
	@Autowired private TokenService tokenService;
	
	/**
	 * API 인증 처리 체크
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean checkToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("*** AuthCheckComponent : checkToken() start...");
		
		String accept = StringUtil.trimToEmpty((String) request.getAttribute("ACCEPT"));
		String contentType = StringUtil.trimToEmpty(request.getContentType());
		String token = StringUtil.trimToEmpty(request.getHeader("token"));
		
		logger.info("accept : {}", accept);
		logger.info("contentType : {}", contentType);
		logger.info("token : {}", token);
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setToken(StringUtil.trimToEmpty(token));
		
		/*************************************************************************
		 * Accept / Token 유무 체크
		 *************************************************************************/
		if ("".equals(accept)) {
			this.setSession(loginInfo, request);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/error/" + ErrorCodeConstants.EC_NO_ACCEPT);
			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			}
			
			return false;
		}
		
		if ("".equals(token)) {
			this.setSession(loginInfo, request);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/error/" + ErrorCodeConstants.EC_TOKEN_REQUIRED);
			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			}
			
			return false;
		}
		
		/*************************************************************************
		 * 사용자 API 인증키 및 세션아이디 체크
		 *************************************************************************/
		TokenBase tokenInfo = tokenService.getTokenInfo(token);
		
		if (tokenInfo == null || tokenInfo.getUsrNo() == null
			|| !MgConstants.FLAG_Y.equals(StringUtil.trimToEmpty(tokenInfo.getUseYn()))
			|| !MgConstants.CONF_STAT_CD_FIN.equals(StringUtil.trimToEmpty(tokenInfo.getConfStatCd()))
		) {
			
			this.setSession(loginInfo, request);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/error/" + ErrorCodeConstants.EC_AUTHENTICATION_REQUIRED);
			if (requestDispatcher != null) {
				requestDispatcher.forward(request, response);
			}
			
			return false;
		}
		
		loginInfo.setToken(StringUtil.trimToEmpty(tokenInfo.getToken()));
		loginInfo.setUsrNo(tokenInfo.getUsrNo());
		
		this.setSession(loginInfo, request);
		
		sb.setLength(0);
		sb.append("------------------------------ Session Info Start --------------------------------" + MgConstants.CRLF);
		sb.append("token : " + sessionFactory.getObject().getLoginInfo().getToken() + MgConstants.CRLF);
		sb.append("usrNo : " + sessionFactory.getObject().getLoginInfo().getUsrNo() + MgConstants.CRLF);
		sb.append("--------------------------------------------------------------" + MgConstants.CRLF);
		sb.append("locale language : " + sessionFactory.getObject().getLocale().getLanguage() + MgConstants.CRLF);
		sb.append("loginDateTime : " + DateUtils.getDateByPattern(sessionFactory.getObject().getLoginDateTime(), "yyyy-MM-dd HH:mm:ss") + MgConstants.CRLF);
		sb.append("loginIp : " + sessionFactory.getObject().getLoginIp() + MgConstants.CRLF);
		sb.append("------------------------------ Session Info End --------------------------------" + MgConstants.CRLF);
		
		logger.info(MgConstants.CRLF + sb.toString());
		
		logger.info("*** AuthCheckComponent : checkToken() end...");
		
		return true;
	}
	
	/**
     * 세션 저장
     * @param loginInfo
     * @throws Exception
     */
    public void setSession(LoginInfo loginInfo, HttpServletRequest request) throws Exception {
    	request.getSession().invalidate();
    	
    	sessionFactory.getObject().setLoginInfo(loginInfo);
		sessionFactory.getObject().setLoginIp(request.getRemoteAddr());
		sessionFactory.getObject().setLoginDateTime(new Date());
		sessionFactory.getObject().setLocale((Locale) request.getAttribute("locale"));
    }
}
