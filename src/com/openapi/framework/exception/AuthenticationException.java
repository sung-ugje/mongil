/**
 * AuthenticationException
 */
package com.openapi.framework.exception;

import com.openapi.framework.utils.MessageUtils;




public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -7115005488292051332L;
	/**
	 * 예외코드
	 */
	private String code;
	/**
	 * 예외 메세지
	 */
	private String message;

	/**
	 * Constructor
	 * @param code
	 * @param message
	 */
	public AuthenticationException(String code) {
		this.code = code;
		this.message = MessageUtils.getMessage(code);
	}
	
	/**
	 * Constructor
	 * @param code
	 * @param message
	 */
	public AuthenticationException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 필드 {@link #code}의 값을 반환한다.
	 * @return {@link #code}의 값
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #code}의 값을 지정한다.
	 * @param code 필드 {@link #code}의 값
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 필드 {@link #message}의 값을 반환한다.
	 * @return {@link #message}의 값
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #message}의 값을 지정한다.
	 * @param message 필드 {@link #message}의 값
	 */
	public void setMessage(String message) {
		this.message = message;
	}


}
