/**
 * 
 */
package com.openapi.domain.common;

import java.io.Serializable;

import com.openapi.framework.utils.MessageUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("success")
public class Success implements Serializable {

	private static final long serialVersionUID = -6048529659509271813L;
	/**
	 * 성공코드
	 */
	private final String code = "200";
	/**
	 * 성공 메세지
	 */
	private String message = MessageUtils.getMessage("MSG_01000");

	/**
	 * Constructor
	 */
	public Success() {}
	
	/**
	 * Constructor
	 */
	public Success(String message) {
		this.message = message;
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

	/**
	 * 필드 {@link #code}의 값을 반환한다.
	 * @return {@link #code}의 값
	 */
	public String getCode() {
		return code;
	}


}
