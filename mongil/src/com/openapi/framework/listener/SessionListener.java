/**
 * 세션 Listener
 */
package com.openapi.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionListener implements HttpSessionListener {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public static int activeSessions = 0;

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		activeSessions++;
		
		logger.info("*** session ( " + sessionEvent.getSession().getId() +  " ) created - count : " + activeSessions + " ***" );
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		if (activeSessions > 0) {
			activeSessions--;
		}
		
		logger.info("*** session ( " + sessionEvent.getSession().getId() +  " ) destroyed - count : " + activeSessions + " ***");
	}
}