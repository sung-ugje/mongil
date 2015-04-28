/**
 * 공통코드 Mapper
 */
package com.openapi.mapper.common;

import java.util.List;

import com.openapi.domain.common.CommonCode;

import org.springframework.stereotype.Repository;

@Repository
public interface CommonCodeMapper {

	/**
	 * 공통코드 목록조회
	 * @param commonCode
	 * @return
	 * @throws Exception
	 */
	public List<CommonCode> selectCommonCodeList(CommonCode commonCode) throws Exception;
}
