/**
 * 프로그램ID Mapper
 */
package com.openapi.mapper.common;

import java.util.List;

import com.openapi.domain.common.ProgramId;

import org.springframework.stereotype.Repository;


@Repository
public interface ProgramIdMapper {

	/**
	 * 캐쉬에 담기위한 프로그램ID 목록 조회
	 * @return
	 * @throws Exception
	 */
	public List<ProgramId> selectPgmIdList() throws Exception;
}
