/**
 * Base 스케쥴러
 */
package com.openapi.framework.scheduler;

import javax.annotation.Resource;

import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.cache.CommonCodeCache;
import com.openapi.framework.cache.ProgramIdCache;
import com.openapi.framework.validator.Validator;

import org.slf4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseScheduler {

	@Log
	protected Logger logger;
	
	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;
	
	@Autowired protected Validator validator;
	@Autowired protected ProgramIdCache programIdCache;
	@Autowired protected CommonCodeCache commonCodeCache;
	
	protected StringBuilder sb = new StringBuilder();
}
