/**
 * 
 */
package com.openapi.domain.common;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("failure")
public class Failure implements Serializable {

	private static final long serialVersionUID = -8602702862740266092L;
	/**
	 * 오류코드
	 */
	private String code = "";
	/**
	 * 오류 메세지
	 */
	private String message = "";

	/**
	 * Constructor
	 */
	public Failure(String code, String message) {
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
