/**
 * Base ServiceImpl
 */
package com.openapi.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.cache.CommonCodeCache;
import com.openapi.framework.cache.ProgramIdCache;
import com.openapi.framework.validator.Validator;

import org.slf4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseServiceImpl {
	
	@Log
	protected Logger logger;
	
	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;

	@Autowired protected HttpServletRequest request;
	@Autowired protected Validator validator;
	@Autowired protected ProgramIdCache programIdCache;
	@Autowired protected CommonCodeCache commonCodeCache;
	
	protected StringBuilder sb = new StringBuilder();
}
