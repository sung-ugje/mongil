/**
 * 서블릿 컨텍스트 Listener
 */
package com.openapi.framework.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SvltContextListener implements ServletContextListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/*서버 시작 시간*/
		MgConstants.SERVER_START_TIME = System.currentTimeMillis();
		
		logger.info("*** Server Start Time : {}", DateUtils.getDateByPattern(new Date(MgConstants.SERVER_START_TIME), "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//
	}
}