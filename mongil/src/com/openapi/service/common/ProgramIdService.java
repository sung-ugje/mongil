/**
 * 프로그램ID Service
 */
package com.openapi.service.common;

import java.util.Map;

public interface ProgramIdService {

	/**
	 * 캐쉬에 담기위한 프로그램ID 목록 조회
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getPgmIdMap() throws Exception;
	
	/**
	 * 프로그램ID 캐쉬 초기화
	 * @throws Exception
	 */
	public void initPgmIdMap() throws Exception;
}
