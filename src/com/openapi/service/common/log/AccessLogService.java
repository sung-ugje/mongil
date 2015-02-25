/**
 * 오픈API 접속로그 Service I/F
 */
package com.openapi.service.common.log;


public interface AccessLogService {

	/**
	 * 접속로그 등록
	 * @param failYn : 실패여부
	 * @param failCause : 실패사유
	 * @param jsonParam : 등록/수정 JSON 파라미터
	 * @throws Exception
	 */
	public void insertAccessLog(String failYn, String failCause, String jsonParam) throws Exception;
}
