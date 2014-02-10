/**
 * 헤더정보 체크 및 API 인증유무 체크
 */
package com.openapi.framework.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.openapi.domain.token.TokenBase;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.component.BaseComponent;
import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.exception.AuthenticationException;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.utils.StringUtil;
import com.openapi.service.token.TokenService;


/*@Aspect
@Component*/
public class AuthKeyCheckAspect extends BaseComponent {
	
	@Log
	private Logger logger;
	
	@Autowired private HttpServletRequest request;
	@Autowired private TokenService tokenService;
	
	@Pointcut("@annotation(com.openapi.framework.annotation.AuthKeyCheck)")
	private void authKeyCheck() {}
	
	/*@Before("authKeyCheck()")
	public void checkParameters() throws Throwable {
		logger.info("*** OPEN API Aspect : checkParameters() start...");
		logger.info("*** OPEN API Aspect : checkParameters() end...");
	}*/
	
	@Around("authKeyCheck()")
	public Object process(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("*** AuthKeyCheckAspect : authKeyCheck() start...");
		
		Object resObj = null;
		
		String contentType = StringUtil.trimToEmpty(request.getContentType());
		String token = StringUtil.trimToEmpty(request.getHeader("token"));
		
		logger.debug("contentType : {}", contentType);
		logger.debug("auth key : {}", token);
		
		/*ConentType Check*/
		if ("".equals(contentType)) {
			throw new FailureException(ErrorCodeConstants.EC_NO_ACCEPT);
		}
		
		/*Token Check*/
		if ("".equals(token)) {
			throw new AuthenticationException(ErrorCodeConstants.EC_TOKEN_REQUIRED);
		}
		
		/*Auth Check*/
		TokenBase tokenInfo = tokenService.getTokenInfo(token);
		
		if (tokenInfo == null
			|| "".equals(StringUtil.trimToEmpty(String.valueOf(tokenInfo.getLoginUsrNo())))
			|| !MgConstants.FLAG_Y.equals(StringUtil.trimToEmpty(tokenInfo.getUseYn()))
			|| !MgConstants.CONF_STAT_CD_FIN.equals(StringUtil.trimToEmpty(tokenInfo.getConfStatCd()))
		) {
			
			throw new AuthenticationException(ErrorCodeConstants.EC_AUTHENTICATION_REQUIRED);
		}
		
		logger.info("*** AuthKeyCheckAspect : authKeyCheck() end...");
		
		resObj = pjp.proceed();
		
		return resObj;
	}
}
