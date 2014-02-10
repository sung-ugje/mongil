/**
 * Base Controller
 */
package com.openapi.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.openapi.framework.annotation.Log;
import com.openapi.framework.cache.CommonCodeCache;
import com.openapi.framework.cache.ProgramIdCache;
import com.openapi.framework.validator.Validator;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

	@Log
	protected Logger logger;

	@Autowired protected ServletContext servletContext;
	@Autowired protected HttpServletRequest request;
	
	@Autowired protected Validator validator;
	@Autowired protected ProgramIdCache programIdCache;
	@Autowired protected CommonCodeCache commonCodeCache;
	
	public static final String AFTER_ACTION_VIEW = "_common/after_action_process";
	public static final String AFTER_AJAX_ACTION_VIEW = "_common/after_ajax_action_process";
	public static final String SUCCESS_CODE = "200";
	public static final String SUCCSESS_MESSAGE = "SUCCESS";
	public static final String FAIL_MESSAGE = "FAIL";
	
	protected StringBuilder sb = new StringBuilder();
}