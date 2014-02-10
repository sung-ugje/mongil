/**
 * Base Component
 */
package com.openapi.framework.component;

import javax.annotation.Resource;

import com.openapi.domain.session.SessionInfo;
import com.openapi.framework.annotation.Log;

import org.slf4j.Logger;
import org.springframework.beans.factory.ObjectFactory;


public class BaseComponent {
	
	@Log
	protected Logger logger;
	
	@Resource(name="apiSessionFactory")
	protected ObjectFactory<SessionInfo> sessionFactory;
	
	protected StringBuilder sb = new StringBuilder();
}
