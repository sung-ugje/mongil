/**
 * FailureException
 */
package com.openapi.framework.exception;

import com.openapi.framework.utils.MessageUtils;


/**
 * 서버매부 오류 예외
 * @author ekxkaks
 *
 */
public class FailureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4463446409622724636L;
	/**
	 * 예외 코드
	 */
	private String code;
	/**
	 * 예외메세지
	 */
	private String message;

	/**
	 * Constructor
	 * @param message
	 */
	public FailureException(String code) {
		this.code = code;
		this.message = MessageUtils.getMessage(code);
	}
	
	/**
	 * Constructor
	 * @param message
	 */
	public FailureException(String code, Object...args) {
		this.code = code;
		this.message = MessageUtils.getMessage(code, args);
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
