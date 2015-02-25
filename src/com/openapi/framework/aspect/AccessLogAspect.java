/**
 * 오픈API Controller처리에 대한 접속 로그(성공/실패) 기록 및 오류시 메일발송 Aspect
 */
package com.openapi.framework.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openapi.framework.component.BaseComponent;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.exception.AuthenticationException;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.exception.SimpleMappingException;
import com.openapi.framework.utils.StringUtil;
import com.openapi.service.common.MailSendService;
import com.openapi.service.common.log.AccessLogService;


@Aspect
@Component
public class AccessLogAspect extends BaseComponent {
	
	@Autowired private HttpServletRequest request;
	@Autowired private AccessLogService accessLogService;
	@Autowired private MailSendService mailSendService;
	
	/**
	 * 
	 */
	@Pointcut("execution(* com.openapi.controller.api..*(..))")
	private void accessLogCheck() {}
	
	/**
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around(value="accessLogCheck()")
	public Object process(ProceedingJoinPoint pjp) throws Throwable {
		/**
		 * AuthCheckComponent에 의해 API 인증을 거치지 않는 URL들은 바로 빠져나간다.
		 */
		if (sessionFactory.getObject() == null
			|| !sessionFactory.getObject().isLogin()
			|| "".equals(StringUtil.trimToEmpty(sessionFactory.getObject().getLoginInfo().getToken()))
		) {
			
			return pjp.proceed();
		}
		
		logger.info("*** AccessLogAspect : accessLogCheck() start...");
		
		Object resObj = null;
		
		String jsonParam = "";
		
		if (RequestMethod.POST.toString().equalsIgnoreCase(request.getMethod())
			|| RequestMethod.PUT.toString().equalsIgnoreCase(request.getMethod())
		) {
			
			for (Object args : pjp.getArgs()) {
				jsonParam = args.toString();
			}
		}
		
		String failYn = MgConstants.FLAG_N;
		String failCause = "";
		
		try {
			resObj = pjp.proceed();
			
		} catch (FailureException e) {
			logger.error("AccessLogAspect FailureException log : {}", e.getMessage());
			
			failYn = MgConstants.FLAG_Y;
			failCause = e.getCode() + " : " + e.getMessage();
			
			throw e;
			
		} catch (AuthenticationException e) {
			logger.error("AccessLogAspect AuthenticationException log : {}", e.getMessage());
			
			failYn = MgConstants.FLAG_Y;
			failCause = e.getCode() + " : " + e.getMessage();
			
			throw e;
			
		} catch (SimpleMappingException e) {
			logger.error("AccessLogAspect SimpleMappingException log : {}", e.getMessage());
			
			failYn = MgConstants.FLAG_Y;
			failCause = e.getCode() + " : " + e.getMessage();
			
			throw e;
			
		} catch (DataAccessException e) {
			logger.error("AccessLogAspect DataAccessException log : {}", e.getMessage());
			
			failYn = MgConstants.FLAG_Y;
			failCause = e.getMessage();
			
			/**
			 * 오류메일 발송
			 */
			this.sendErrorMail(e);
			
			throw e;
			
		} catch (Exception e) {
			logger.error("AccessLogAspect Exception log : {}", e.getMessage());
			
			failYn = MgConstants.FLAG_Y;
			failCause = e.getMessage();
			
			/**
			 * 오류메일 발송
			 */
			this.sendErrorMail(e);
			
			throw e;
			
		} finally {
			/**
			 * 접속 로그 등록
			 */
			accessLogService.insertAccessLog(failYn, failCause, jsonParam);
		}
		
		logger.info("*** AccessLogAspect : accessLogCheck() end...");
		
		return resObj;
	}
	
	/**
	 * 오류 메일 발송
	 * @param e
	 * @throws Exception
	 */
	public void sendErrorMail(Exception e) {
		try {
			mailSendService.sendMailByInternalException(e);
			
		} catch (Exception e1) {
			StringUtil.printExceptionTrace(e1);
		}
	}
}
