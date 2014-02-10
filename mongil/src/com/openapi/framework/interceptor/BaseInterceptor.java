/**
 * Base Interceptor
 */
package com.openapi.framework.interceptor;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.utils.DateUtils;

import org.slf4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Log
	protected Logger logger;
	
	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;
	
	@Resource(name="localeResolver")
	protected SessionLocaleResolver localeResolver;
	
	protected StringBuilder sb = new StringBuilder();
	
	private Long startTimestamp = null;
	private Long endTimestamp = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		startTimestamp = System.currentTimeMillis();
		
		Long filterStartTimestamp = (Long) request.getAttribute("startTimestamp");
		
		Long elapsedTime = null;
		
		if (filterStartTimestamp == null) {
			elapsedTime = 0L;
		} else {
			elapsedTime = startTimestamp - filterStartTimestamp;
		}
		
		logger.info("*** preHandle [" + request.getMethod() + "-S] " + request.getRequestURI() + " : " + DateUtils.getDateByPattern(new Date(startTimestamp), "yyyy-MM-dd HH:mm:ss") + " | elapsed : " + elapsedTime + " ms");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		endTimestamp = System.currentTimeMillis();
		
		Long filterStartTimestamp = (Long) request.getAttribute("startTimestamp");
		
		Long elapsedTime = null;
		
		if (filterStartTimestamp == null) {
			elapsedTime = endTimestamp - startTimestamp;
		} else {
			elapsedTime = endTimestamp - filterStartTimestamp;
		}
		
		logger.info("*** postHandle [" + request.getMethod() + "-E] " + request.getRequestURI() + " : " + DateUtils.getDateByPattern(new Date(endTimestamp), "yyyy-MM-dd HH:mm:ss") + " | elapsed : " + elapsedTime + " ms");
	}
}
