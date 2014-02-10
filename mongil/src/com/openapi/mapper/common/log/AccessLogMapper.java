/**
 * 오픈API 접속로그 Mapper
 */
package com.openapi.mapper.common.log;

import com.openapi.domain.common.log.AccessLog;

import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogMapper {

	/**
	 * 접속 로그 등록
	 * @param accessLog
	 * @throws Exception
	 */
	public void insertAccessLog(AccessLog accessLog) throws Exception;
}
