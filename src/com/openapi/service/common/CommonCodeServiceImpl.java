/**
 * 공통코드 ServiceImpl
 */
package com.openapi.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.openapi.domain.common.CommonCode;
import com.openapi.framework.utils.StringUtil;
import com.openapi.mapper.common.CommonCodeMapper;
import com.openapi.service.BaseServiceImpl;

@Service
public class CommonCodeServiceImpl extends BaseServiceImpl implements
		CommonCodeService {

	@Autowired private CommonCodeMapper commonCodeMapper;

	/**
	 * 공통코드 목록조회
	 * 
	 * @param langTp
	 * @param useYn
	 * @return
	 * @throws Exception
	 */
	@Override
	@Cacheable(value = "cmmCodeCache")
	public Map<String, List<CommonCode>> getCommonCodeGrpMap() throws Exception {
		Map<String, List<CommonCode>> map = new HashMap<String, List<CommonCode>>();

		CommonCode commonCode = new CommonCode();

		List<CommonCode> list = commonCodeMapper.selectCommonCodeList(commonCode);

		List<CommonCode> cdGrpList = null;

		if (list != null && list.size() > 0) {
			String tmpCdGrp = "";

			for (CommonCode rsvo : list) {
				if ("".equals(tmpCdGrp)) {
					tmpCdGrp = StringUtil.trimToEmpty(rsvo.getCdGrp());
					cdGrpList = new ArrayList<CommonCode>();
				}

				/*
				 * logger.debug("tmpCdGrp : {}", tmpCdGrp);
				 * logger.debug("cdGrp : {}", rsvo.getCdGrp());
				 * logger.debug("cdName : {}", rsvo.getCdNm());
				 */

				if (tmpCdGrp.equals(StringUtil.trimToEmpty(rsvo.getCdGrp()))) {
					cdGrpList.add(rsvo);

				} else {
					map.put(tmpCdGrp, cdGrpList);

					cdGrpList = new ArrayList<CommonCode>();
					cdGrpList.add(rsvo);
				}

				tmpCdGrp = StringUtil.trimToEmpty(rsvo.getCdGrp());
			}
		}

		return map;
	}

	/**
	 * 공통코드 초기화
	 * 
	 * @param cdGrp
	 * @throws Exception
	 */
	@Override
	public void initCommonCodeGrpMap() throws Exception {
		logger.debug("commonCodeCache initCommonCodeGrpMap() Start...");
	}
	
}
