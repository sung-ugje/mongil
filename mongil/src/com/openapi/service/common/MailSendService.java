/**
 * 메일 발송 Service
 */
package com.openapi.service.common;


public interface MailSendService {

	/**
	 * 오픈API 내부오류 발생시 관리자에게 메일 발송
	 * @param e : Exception
	 * @throws Exception
	 */
	public void sendMailByInternalException(Exception e) throws Exception;
}
