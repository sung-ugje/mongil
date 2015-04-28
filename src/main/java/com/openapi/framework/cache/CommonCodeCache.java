/**
 * 공통코드 캐쉬
 */
package com.openapi.framework.cache;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openapi.domain.common.CommonCode;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.utils.StringUtil;
import com.openapi.service.common.CommonCodeService;


@Component
public class CommonCodeCache implements InitializingBean {
	
	@Log
	private Logger logger;
	
	@Autowired private CommonCodeService commonCodeService;

	public void afterPropertiesSet() throws Exception {
		logger.info("*** CommonCodeCache load() start...");
		
		this.load();
		
		logger.info("*** CommonCodeCache load() end...");
	}
	
	/**
	 * 캐쉬 적재
	 * @throws Exception
	 */
	public void load() throws Exception {
		commonCodeService.getCommonCodeGrpMap();
	}
	
	/**
	 * 캐쉬 재적재
	 * @throws Exception
	 */
	public void reload() throws Exception {
		logger.info("*** CommonCodeCache reload() start...");
		
		this.initCommonCode();
		this.load();
		
		logger.info("*** CommonCodeCache reload() end...");
	}
	
	/**
	 * 캐쉬에서 코드그룹에 해당하는 코드목록 조회
	 * @param cdGrp
	 * @return
	 */
	public List<CommonCode> getCommonCodeList(String cdGrp) throws Exception {
		Map<String, List<CommonCode>> map = commonCodeService.getCommonCodeGrpMap();
		
		return map.get(cdGrp);
	}
	
	/**
	 * 캐쉬에서 코드그룹과 코드에 해당하는 CommonCode 조회
	 * @param cdGrp
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public CommonCode getCommonCode(String cdGrp, String cd) throws Exception {
		return this.getCommonCode(cdGrp, cd, "KO");
	}
	
	/**
	 * 캐쉬에서 코드그룹과 코드에 해당하는 CommonCode 조회
	 * @param cdGrp 
	 * @param cd
	 * @return
	 * @throws Exception
	 */
	public CommonCode getCommonCode(String cdGrp, String cd, String langTp) throws Exception {
		CommonCode resVo = null;
		
		Map<String, List<CommonCode>> map = commonCodeService.getCommonCodeGrpMap();
		
		if (map != null && map.size() > 0) {
			List<CommonCode> list = map.get(cdGrp);
			
			if (list != null && list.size() > 0) {
				for (CommonCode rsvo : list) {
					if (StringUtil.trimToEmpty(cd).equals(StringUtil.trimToEmpty(rsvo.getCd()))
						&& langTp.equalsIgnoreCase(StringUtil.trimToEmpty(rsvo.getLangTp()))
					) {
						resVo = rsvo;
						break;
					}
				}
			}
		}
		
		return resVo;
	}
	
	public void initCommonCode() throws Exception {
		commonCodeService.initCommonCodeGrpMap();
	} 
}
