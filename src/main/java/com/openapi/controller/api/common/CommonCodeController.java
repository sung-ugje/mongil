/**
 * 공통코드 Controller
 */
package com.openapi.controller.api.common;

import java.util.List;

import com.openapi.controller.BaseOpenApiController;
import com.openapi.domain.common.CommonCode;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class CommonCodeController extends BaseOpenApiController {

	private String[] accessCodes = ArrayUtils.toArray(
		/*사용자*/"CG_us001"
	);

	/**
	 * 해당 그룹코드에 대한 코드 목록 조회
	 * @param cdGrp : 공통코드 그룹코드
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/code/{cdGrp}", method=RequestMethod.GET)
	@ResponseBody
	public List<CommonCode> getCodeList(
		@PathVariable("cdGrp") String cdGrp
	) throws Exception {

		validator.checkEmpty(cdGrp, "그룹코드");
		
		/*허가된 그룹코드만 조회 가능*/
		if (!ArrayUtils.contains(accessCodes, cdGrp)) {
			//throw new FailureException("EC20100");
		}
		
		/*캐쉬에서 목록 조회*/
		List<CommonCode> codeList = commonCodeCache.getCommonCodeList(cdGrp);

		return codeList;
	}
}
