/**
 * SimpleMappingExceptionResolver for OPEN API
 */
package com.openapi.framework.wrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.openapi.framework.constants.ErrorCodeConstants;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.StringUtil;


public class SimpleMappingExceptionResolverWrapper extends SimpleMappingExceptionResolver {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Constructor
	 */
	public SimpleMappingExceptionResolverWrapper() {
		logger.info("*** SimpleMappingExceptionResolverWrapper start...");
	}

	@Override
	public ModelAndView doResolveException(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex
	) {
		
		logger.info("*** SimpleMappingExceptionResolverWrapper.doResolveException() start...");
		
		//com.openapi.framework.utils.StringUtil.printExceptionTrace(ex);
		
		String viewName = super.determineViewName(ex, request);
		
		logger.debug("viewName : {}", viewName);
		
		String errorCode = "";
		
		try {
			if (ex instanceof NoSuchRequestHandlingMethodException) {
				logger.error("*** NoSuchRequestHandlingMethodException happened.");
				
				errorCode = ErrorCodeConstants.EC_NOT_FOUND;
				
				request.setAttribute("statusCode", "404");
				
			} else if (ex instanceof HttpRequestMethodNotSupportedException) {
				logger.error("*** HttpRequestMethodNotSupportedException happened.");
				
				errorCode = ErrorCodeConstants.EC_METHOD_NOT_ALLOWED;
				
				request.setAttribute("statusCode", "405");
				
			} else if (ex instanceof HttpMediaTypeNotSupportedException) {
				logger.error("*** HttpMediaTypeNotSupportedException happened.");
				
				errorCode = ErrorCodeConstants.EC_UNSUPPORTED_MEDIA_TYPE;
				
				request.setAttribute("statusCode", "415");
				
			} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
				logger.error("*** HttpMediaTypeNotAcceptableException happened.");
				
				errorCode = ErrorCodeConstants.EC_NOT_ACCEPTABLE_MEDIA_TYPE;
				
				request.setAttribute("statusCode", "406");
				
			} else if (ex instanceof MissingServletRequestParameterException) {
				logger.error("*** MissingServletRequestParameterException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof ServletRequestBindingException) {
				logger.error("*** ServletRequestBindingException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof ConversionNotSupportedException) {
				logger.error("*** ConversionNotSupportedException happened.");
				
				errorCode = ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR;
				
				request.setAttribute("statusCode", "500");
				
			} else if (ex instanceof TypeMismatchException) {
				logger.error("*** TypeMismatchException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof HttpMessageNotReadableException) {
				logger.error("*** HttpMessageNotReadableException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof HttpMessageNotWritableException) {
				logger.error("*** HttpMessageNotWritableException happened.");
				
				errorCode = ErrorCodeConstants.EC_INTERNAL_SERVER_ERROR;
				
				request.setAttribute("statusCode", "500");
				
			} else if (ex instanceof MethodArgumentNotValidException) {
				logger.error("*** MethodArgumentNotValidException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof BindException) {
				logger.error("*** BindException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
				
			} else if (ex instanceof MissingServletRequestPartException) {
				logger.error("*** MissingServletRequestPartException happened.");
				
				errorCode = ErrorCodeConstants.EC_BAD_REQUEST;
				
				request.setAttribute("statusCode", "400");
			}
			
			/**
			 * ErrorController로 오류 전달
			 */
			logger.info("errorCode : {}", errorCode);
			
			if (!"".equals(errorCode)) {
				request.setAttribute("code", errorCode);
				request.setAttribute("message", MessageUtils.getMessage(errorCode));
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/api/error/" + StringUtil.trimToEmpty(errorCode));
				
				if (requestDispatcher != null) {
					requestDispatcher.forward(request, response);
				}
				
				return null;
				
			} else {
				return super.getModelAndView(viewName, ex, request);
			}
			
		} catch (Exception handlerException) {
			logger.warn("*** Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
			
			return super.getModelAndView(viewName, ex, request);
		}
	}
}
