package com.openapi.framework.component;

import com.openapi.domain.session.SessionInfo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * API 로그인 세션
 */
@Scope(value="session")
@Component
public class ApiLoginSession extends SessionInfo {

	private static final long serialVersionUID = -2272974141484725894L;
	
	/**
	 * Constructor
	 */
	public ApiLoginSession() {}
}