/**
 * 공통코드 Service
 */
package com.openapi.service.common;

import java.util.List;
import java.util.Map;

import com.openapi.domain.common.CommonCode;


public interface CommonCodeService {
	
	/**
	 * 공통코드 목록조회
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<CommonCode>> getCommonCodeGrpMap() throws Exception;
	
	/**
	 * 공통코드 초기화
	 * @param cdGrp
	 * @throws Exception
	 */
	public void initCommonCodeGrpMap() throws Exception;
}
