/**
  * 프로그램ID : ServiceImpl
 */
package com.openapi.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openapi.domain.common.ProgramId;
import com.openapi.mapper.common.ProgramIdMapper;
import com.openapi.service.BaseServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class ProgramIdServiceImpl extends BaseServiceImpl implements ProgramIdService {
	
	@Autowired private ProgramIdMapper programIdMapper;
	
	/**
	 * 캐쉬에 담기위한 프로그램ID 목록 조회
	 * @return
	 * @throws Exception
	 */
	@Override
	@Cacheable(value="pgmIdCache")
	public Map<String, String> getPgmIdMap() throws Exception {
		Map<String, String> pgmIdMap = new HashMap<String, String>();
		
		List<ProgramId> list = programIdMapper.selectPgmIdList();
		
		if (list != null && list.size() > 0) {
			for (ProgramId rsvo : list) {
				pgmIdMap.put(StringUtils.trimToEmpty(rsvo.getMapperApi()), StringUtils.trimToEmpty(rsvo.getPgmId()));
			}
		}
		
		return pgmIdMap;
	} 
	
	/**
	 * 프로그램ID 캐쉬 초기화
	 * @throws Exception
	 */
	@Override
	@CacheEvict(value="pgmIdCache", allEntries=true)
	public void initPgmIdMap() throws Exception {
		logger.info("*** pgmIdCache initPgmIdMap() start...");
	} 
	
}
