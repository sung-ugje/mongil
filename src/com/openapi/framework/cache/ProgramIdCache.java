/**
 * 프로그램ID를 담고 있는 캐쉬
 */
package com.openapi.framework.cache;

import java.util.Map;

import com.openapi.framework.annotation.Log;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.service.common.ProgramIdService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProgramIdCache implements InitializingBean {
	
	@Log
	private Logger logger;
	
	@Autowired private ProgramIdService programIdService;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("*** ProgramIdCache load() start...");
		
		this.load();
		
		logger.info("*** ProgramIdCache load() end...");
	}
	
	/**
	 * 캐쉬 적재
	 * @throws Exception
	 */
	public void load() throws Exception {
		programIdService.getPgmIdMap();
	}
	
	/**
	 * 캐쉬 재적재
	 * @throws Exception
	 */
	public void reload() throws Exception {
		logger.info("*** ProgramIdCache reload() start...");
		
		this.initPgmId();
		this.load();
		
		logger.info("*** ProgramIdCache reload() end...");
	}
	
	/**
	 * 가져온 맵에서 코드에 해당하는 값조회
	 * @param code
	 * @return
	 */
	public String getPgmId(String code) throws Exception {
		Map<String, String> pgmIdMap = programIdService.getPgmIdMap();
		
		String pgmId = StringUtils.trimToEmpty(pgmIdMap.get(code));
		
		if ("".equals(pgmId)) {
			throw new RuntimeException(MessageUtils.getMessage("EC20101", code));
		}
		
		return pgmId;
	}
	
	public void initPgmId() throws Exception {
		programIdService.initPgmIdMap();
	}
}
