/**
 * SimpleMappingException
 */
package com.openapi.framework.exception;

import com.openapi.framework.utils.MessageUtils;


public class SimpleMappingException extends RuntimeException {

	private static final long serialVersionUID = -58681563759189028L;
	/**
	 * 상태 코드
	 */
	private int statusCode;
	/**
	 * 예외 코드
	 */
	private String code;
	/**
	 * 예외 메세지
	 */
	private String message;

	/**
	 * Constructor
	 * @param code 예외코드
	 * @param message 예외 메세지
	 */
	public SimpleMappingException(Integer statusCode, String code) {
		this.statusCode = statusCode;
		this.code = code;
		this.message = MessageUtils.getMessage(code);
	}
	
	/**
	 * Constructor
	 * @param statusCode 상태코드
	 * @param code 예외코드
	 * @param message 예외 메세지
	 */
	public SimpleMappingException(Integer statusCode, String code, String message) {
		this.statusCode = statusCode;
		this.code = code;
		this.message = message;
	}

	/**
	 * 필드 {@link #statusCode}의 값을 반환한다.
	 * @return {@link #statusCode}의 값
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #statusCode}의 값을 지정한다.
	 * @param statusCode 필드 {@link #statusCode}의 값
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
