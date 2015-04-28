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
		/*매입 구분 코드*/"CG_MPD003", /*상품 유형 코드*/"CG_MPD004", /*판매방식 코드*/"CG_MPD005", /*과면세 구분 코드*/"CG_MPD006", /*체적 단위 코드*/"CG_MPD007",
		/*판매 상태 코드*/"CG_MPD008", /*상품평 노출 유형 코드*/"CG_MPD009",
		/*구매 수량 제한 유형 코드*/"CG_MPD010", /*배송 방법 코드*/"CG_MPD011", 	/*배송 처리 유형 코드*/"CG_MPD012", /*배송 상품 구분 코드*/"CG_MPD013", /*배송 기일 코드*/"CG_MPD014",
		/*픽업 정보 출력 수단 코드*/"CG_MPD015", /*승인상태코드*/"CG_MPD017", /*가격구분 코드*/"CG_MPD019",
		/*컨텐츠 구분코드*/"CG_MPD021", /*결제수단코드*/"CG_MPD025", /*사용여부 코드*/"CG_MPD027", /*연결브랜드유형코드*/"CG_MPD028", /*브랜드 사용전시 언어코드*/"CG_MPD029",
		/*매장유형코드*/"CG_MPD031", /*하위연결유형코드*/"CG_MPD032", /*리스트정렬유형코드*/"CG_MPD033", /*매장연결유형코드*/"CG_MPD034", /*이동프레임코드*/"CG_MPD035", /*브랜드컨텐츠유형코드*/"CG_MPD036",
		/*상품구분 코드*/"CG_MPD051", /*안전인증 대상코드*/"CG_MPD053",
		/*모바일 템플릿 코드*/"CG_MPD062", /*전시노출 유형코드*/"CG_MPD066",
		/*상품반려유형코드*/"CG_MPD074",
		/*표시단위 코드*/"CG_MPD081", /*세일타입코드*/"CG_MPD083", /*품목분류 코드*/"CG_MPD089",
		/*배송비 유형*/"CG_MC014", /*배송비 부여방식*/"CG_MC015"
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
