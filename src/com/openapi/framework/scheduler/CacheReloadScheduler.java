/**
 * 캐쉬 리로드 스케쥴러
 */
package com.openapi.framework.scheduler;

import com.openapi.framework.utils.MessageUtils;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CacheReloadScheduler extends BaseScheduler {
	
	/**
	 * 매일 0시 0분 0초에 캐쉬 리로드
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void reloadCahce() throws Exception {
		logger.info("*** CacheReloadScheduler start...");
		
		programIdCache.reload();
		commonCodeCache.reload();
		
		logger.info(MessageUtils.getMessage("MSG_01002"));
		
		logger.info("*** CacheReloadScheduler end...");
	}
}
