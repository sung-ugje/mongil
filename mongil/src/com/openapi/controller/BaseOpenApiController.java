/**
 * Base Open API Controller
 */
package com.openapi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.openapi.domain.common.Failure;
import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.exception.AuthenticationException;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.exception.SimpleMappingException;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.StringUtil;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseOpenApiController extends BaseController {

	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;
	
	/**
	 * FailureException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(FailureException.class)
	@ResponseStatus(value=HttpStatus.METHOD_FAILURE)
	@ResponseBody
	public Failure handleException(HttpServletResponse response, FailureException e) {
		logger.error("*** FailureException Handle : " + e.getMessage());
		
		//StringUtil.printExceptionTrace(e);
		
		return new Failure(e.getCode(), e.getMessage());
	}
	
	/**
	 * AuthenticationException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Failure handleException(HttpServletResponse response, AuthenticationException e) {
		logger.error("*** AuthenticationException Handle : " + e.getMessage());
		
		//StringUtil.printExceptionTrace(e);
		
		return new Failure(e.getCode(), e.getMessage());
	}
	
	/**
	 * DataAccessException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value=HttpStatus.METHOD_FAILURE)
	@ResponseBody
	@Transactional
	public Failure handleException(HttpServletResponse response, DataAccessException e) {
		logger.error("*** DataAccessException Handle : " + MessageUtils.getMessage(ErrorCodeConstants.EC_DATA_EXCEPTION));
		
		StringUtil.printExceptionTrace(e);
		
		return new Failure(ErrorCodeConstants.EC_DATA_EXCEPTION, MessageUtils.getMessage(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR));
	}
	
	/**
	 * SimpleMappingException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SimpleMappingException.class)
	@ResponseBody
	public Failure handleException(HttpServletResponse response, SimpleMappingException e) {
		response.setStatus(e.getStatusCode());
		
		logger.error("*** SimpleMappingException Handle : " + e.getMessage());
		
		//StringUtil.printExceptionTrace(e);
		
		return new Failure(e.getCode(), e.getMessage());
	}
	
	/**
	 * RuntimeException
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@Transactional
	public Failure handleException(HttpServletResponse response, RuntimeException e) {
		logger.error("*** RuntimeException Handle : " + MessageUtils.getMessage(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR));
		
		StringUtil.printExceptionTrace(e);
		
		return new Failure(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR, MessageUtils.getMessage(ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR));
	}
}