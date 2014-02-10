/**
 * 메일 발송 Mapper
 */
package com.openapi.mapper.common;

import com.openapi.domain.common.MailSendHistory;

import org.springframework.stereotype.Repository;


@Repository
public interface MailSendMapper {

	/**
	 * 메일 발송을 위한 데이터를 등록한다.
	 * @param mailSendHistory
	 * @throws Exception
	 */
	public void insertMailSendHist(MailSendHistory mailSendHistory) throws Exception;
}
