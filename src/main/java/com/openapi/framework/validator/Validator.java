/**
 * 어노테이션 Validation(1차 Validation) 및 기타 Validation 처리 Component
 */
package com.openapi.framework.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openapi.domain.common.CommonCode;
import com.openapi.framework.annotation.FieldCheck;
import com.openapi.framework.annotation.Log;
import com.openapi.framework.cache.CommonCodeCache;
import com.openapi.framework.component.BaseComponent;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.exception.FailureException;
import com.openapi.framework.utils.DateUtils;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.StringUtil;


@Component
public class Validator extends BaseComponent {
	
	@Log
	private Logger logger;
	
	@Autowired private HttpServletRequest request;
	@Autowired private CommonCodeCache commonCodeCache;
	
	private StringBuilder sb = new StringBuilder();
	
	/**
	 * 처리해야 할 파라미터 데이터 또는 JSON/XML 데이터 존재유무 Validation
	 * @param obj : 체크할 객체
	 * @param strings : 오류발생시 출력해야 할 객체명
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void checkEmpty(Object obj, Object...args) throws Exception {
		if (obj instanceof String) {
			if ("".equals(StringUtil.trimToEmpty((String) obj))) {
				throw new FailureException("EC01100", args);
			}
			
		} else {
			if (obj instanceof List) {
				if (obj == null || ((List) obj).size() == 0) {
					throw new FailureException("EC01100", args);
				}
				
			} else {
				if (obj == null) {
					throw new FailureException("EC01100", args);
				}
			}
		}
	}
	
	/**
	 * FieldCheck Annotation Validation (for java.util.List)
	 * @param list : java.util.List
	 * @throws Exception
	 */
	public void fieldCheckValidation(List<?> list) throws Exception {
		if (list == null) {
			throw new RuntimeException(MessageUtils.getMessage("EC20105"));
		}
		
		for (Object obj : list) {
			this.fieldCheckValidation(obj);
		}
	}
	
	/**
	 * FieldCheck Annotation Validation (for VO)
	 * @param obj : VO
	 * @throws Exception
	 */
	public void fieldCheckValidation(Object obj) throws Exception {
		if (obj == null) {
			throw new RuntimeException(MessageUtils.getMessage("EC20106"));
		}
		
		logger.info("*** Annotation field check start...");
		logger.info("Object Name : {}", obj.getClass().getName());
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		if (fields != null && fields.length > 0) {
			for (int i=0; i<fields.length; i++) {
				Field field = fields[i];
				
				field.setAccessible(true);
				
				String fieldName = StringUtil.trimToEmpty(field.getName());
				
				FieldCheck fieldCheckClass = field.getAnnotation(FieldCheck.class);
				
				if (fieldCheckClass == null) continue;
				
				/**
				 * 파라미터 값
				 */
				Object objVal = field.get(obj);
				String fieldValue = "";
				
				if (objVal != null) {
					fieldValue = StringUtil.trimToEmpty(objVal.toString());
				}
				
				Integer fieldValueLength = fieldValue.getBytes("UTF-8").length;
				
				String setMethodName = "set" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
				
				Method setMethod = obj.getClass().getMethod(setMethodName, new Class[]{field.getType()});
				
				/******************************** Annotation Field Start ************************************/
				String fieldKorNm = StringUtil.trimToEmpty(fieldCheckClass.fieldKorNm());
				if ("".equals(fieldKorNm)) {
					fieldKorNm = fieldName;
				} else {
					fieldKorNm = fieldKorNm + " (" + fieldName + ")";
				}
				
				Boolean isNotEmpty = fieldCheckClass.isNotEmpty();
				Integer minLength = fieldCheckClass.minLength();
				Integer maxLength = fieldCheckClass.maxLength();
				String minValue = StringUtil.trimToEmpty(fieldCheckClass.minValue());
				String maxValue = StringUtil.trimToEmpty(fieldCheckClass.maxValue());
				String code = StringUtil.trimToEmpty(fieldCheckClass.code());
				String dateFormat = StringUtil.trimToEmpty(fieldCheckClass.dateFormat());
				Double numberScale = fieldCheckClass.numberScale();
				String startValue = StringUtil.trimToEmpty(fieldCheckClass.startValue());
				String defaultValue = StringUtil.trimToEmpty(fieldCheckClass.defaultValue());
				Boolean isBanWord = fieldCheckClass.isBanWord();
				Boolean isHtmlSpecialChars = fieldCheckClass.isHtmlSpecialChars();
				Boolean isOnlyNumber = fieldCheckClass.isOnlyNumber();
				
				sb.setLength(0);
				sb.append("fieldName : " + fieldName + MgConstants.CRLF);
				sb.append("fieldKorNm : " + fieldKorNm + MgConstants.CRLF);
				sb.append("fieldValue : " + fieldValue + MgConstants.CRLF);
				sb.append("fieldValueLength : " + fieldValueLength + MgConstants.CRLF);
				//sb.append("annotationType : " + fieldCheckClass.annotationType() + OAConstants.CRLF);
				sb.append("-------------------------------------------" + MgConstants.CRLF);
				
				if (isNotEmpty) {
					sb.append("isNotEmpty : " + isNotEmpty + MgConstants.CRLF);
				}
				
				if (minLength != 0L) {
					sb.append("minLength : " + minLength + MgConstants.CRLF);
				}
				
				if (maxLength != 0L) {
					sb.append("maxLength : " + maxLength + MgConstants.CRLF);
				}
				
				if (!"".equals(minValue)) {
					sb.append("minValue : " + minValue + MgConstants.CRLF);
				}
				
				if (!"".equals(maxValue)) {
					sb.append("maxValue : " + maxValue + MgConstants.CRLF);
				}
				
				if (!"".equals(code)) {
					sb.append("code : " + code + MgConstants.CRLF);
				}
				
				if (!"".equals(dateFormat)) {
					sb.append("dateFormat : " + dateFormat + MgConstants.CRLF);
				}
				
				if (numberScale != 0.0) {
					sb.append("numberScale : " + numberScale + MgConstants.CRLF);
				}
				
				if (!"".equals(startValue)) {
					sb.append("startValue : " + startValue + MgConstants.CRLF);
				}
				
				if (!"".equals(defaultValue)) {
					sb.append("defaultValue : " + defaultValue + MgConstants.CRLF);
				}
				
				if (isBanWord) {
					sb.append("isBanWord : " + isBanWord + MgConstants.CRLF);
				}
				
				if (isHtmlSpecialChars) {
					sb.append("isHtmlSpecialChars : " + isHtmlSpecialChars + MgConstants.CRLF);
				}
				
				if (isOnlyNumber) {
					sb.append("isOnlyNumber : " + isOnlyNumber + MgConstants.CRLF);
				}
				
				sb.append(MgConstants.CRLF);
				
				//logger.debug(OAConstants.CRLF + sb.toString());
				/******************************** Annotation Field End ************************************/
				
				/**
				 * isNotEmpty()
				 */
				if (isNotEmpty && "".equals(fieldValue)) {
					throw new FailureException("EC01100", fieldKorNm);
				}
				
				/**
				 * minLength
				 */
				if (minLength != 0 && !"".equals(fieldValue)) {
					if (fieldValueLength < minLength) {
						throw new FailureException("EC01101", fieldKorNm, fieldValue, minLength);
					}
				}
				
				/**
				 * maxLength
				 */
				if (maxLength != 0 && !"".equals(fieldValue)) {
					if (fieldValueLength > maxLength) {
						throw new FailureException("EC01102", fieldKorNm, fieldValue, maxLength);
					}
				}
				
				/**
				 * minValue
				 */
				if (!"".equals(minValue) && !"".equals(fieldValue)) {
					if (Long.parseLong(fieldValue) < Long.parseLong(minValue)) {
						throw new FailureException("EC01103", fieldKorNm, fieldValue, minValue);
					}
				}
				
				/**
				 * maxValue
				 */
				if (!"".equals(maxValue) && !"".equals(fieldValue)) {
					if (Long.parseLong(fieldValue) > Long.parseLong(maxValue)) {
						throw new FailureException("EC01104", fieldKorNm, fieldValue, maxValue);
					}
				}
				
				/**
				 * code
				 */
				if (!"".equals(code) && !"".equals(fieldValue)) {
					/*Y or N*/
					if (code.equals("YN")) {
						if (!MgConstants.FLAG_Y.equalsIgnoreCase(fieldValue)
							&& !MgConstants.FLAG_N.equalsIgnoreCase(fieldValue)
						) {
							throw new FailureException("EC01114", fieldKorNm, fieldValue);
						}
						
					/*LIST*/
					} else if (code.startsWith("LIST")) {
						String[] codes = StringUtil.split(code, ":");
						
						if (codes != null && codes.length == 2) {
							String[] checkCodes = StringUtil.split(StringUtil.trimToEmpty(codes[1]), ",");
							
							if (!ArrayUtils.contains(checkCodes, fieldValue)) {
								throw new FailureException("EC01105", fieldKorNm, fieldValue, ArrayUtils.toString(checkCodes));
							}
						}
						
					/*CG_(공통코드)*/
					} else if (code.startsWith("CG_")) {
						List<CommonCode> codeList = commonCodeCache.getCommonCodeList(code);
						
						if (codeList == null || codeList.size() == 0) {
							throw new FailureException("EC01108", fieldKorNm, fieldValue);
						}
						
						CommonCode commonCode = commonCodeCache.getCommonCode(code, fieldValue);
						
						if (commonCode == null || "".equals(commonCode.getCd())) {
							throw new FailureException("EC01113", fieldKorNm, code, fieldValue);
						}
					}
				}
				
				/**
				 * dateFormat
				 */
				if (!"".equals(dateFormat) && !"".equals(fieldValue)) {
					if (!DateUtils.isDateFormat(fieldValue, dateFormat)) {
						throw new FailureException("EC01106", fieldKorNm, fieldValue, dateFormat);
					}
				}
				
				/**
				 * numberScale
				 */
				if (numberScale != 0.0 && !"".equals(fieldValue)) {
					String strNumberScale = String.valueOf(numberScale);
					String[] strNumberScales = StringUtil.split(strNumberScale, ".");
					
					try {
						DecimalFormat df = new DecimalFormat("#.#####################################");
						fieldValue = df.format(Double.parseDouble(fieldValue));
						
					} catch (Exception e) {
						throw new FailureException("EC01109", fieldKorNm, fieldValue);
					}
					
					String[] fvals = StringUtil.split(fieldValue, ".");
					
					if (strNumberScales != null && strNumberScales.length == 2) {
						String strNum1 = StringUtil.trimToEmpty(strNumberScales[0]);
						String strNum2 = StringUtil.trimToEmpty(strNumberScales[1]);
						int num1 = Integer.parseInt(strNum1) - Integer.parseInt(strNum2);
						int num2 = Integer.parseInt(strNum2);
						
						if (fvals.length == 1) {
							if (fvals[0].length() > num1) {
								throw new FailureException("EC01107", fieldKorNm, fieldValue, num1, num2);
							}
						
						} else if (fvals.length == 2) {
							String fval1 = StringUtil.trimToEmpty(fvals[0]);
							String fval2 = StringUtil.trimToEmpty(fvals[1]);
							
							if (fval1.length() > num1
								|| fval2.length() > num2
							) {
								throw new FailureException("EC01107", fieldKorNm, fieldValue, num1, num2);
							}
							
						} else {
							throw new FailureException("EC01107", fieldKorNm, fieldValue, num1, num2); 
						}
						
					} else {
						throw new RuntimeException(MessageUtils.getMessage("EC20107", fieldValue, numberScale));
					}
				}
				
				/**
				 * startValue
				 */
				if (!"".equals(startValue) && !"".equals(fieldValue)) {
					if (!fieldValue.startsWith(startValue)) {
						throw new FailureException("EC01110", fieldKorNm, startValue, fieldValue);
					}
				}
				
				/**
				 * defaultValue
				 */
				if (!"".equals(defaultValue)) {
					if ("".equals(fieldValue)) {
						if (field.getType().isAssignableFrom(String.class)) {
							setMethod.invoke(obj, defaultValue);
							
						} else if (field.getType().isAssignableFrom(Long.class)) {
							setMethod.invoke(obj, Long.parseLong(defaultValue));
							
						} else if (field.getType().isAssignableFrom(Double.class)) {
							setMethod.invoke(obj, Double.parseDouble(defaultValue));
							
						} else if (field.getType().isAssignableFrom(Float.class)) {
							setMethod.invoke(obj, Float.parseFloat(defaultValue));
							
						} else if (field.getType().isAssignableFrom(Integer.class)) {
							setMethod.invoke(obj, Integer.parseInt(defaultValue));
							
						} else if (field.getType().isAssignableFrom(Short.class)) {
							setMethod.invoke(obj, Short.parseShort(defaultValue));
						}
					}
				}
				
				
				/**
				 * isHtmlSpecialChars
				 */
				if (isHtmlSpecialChars && !"".equals(fieldValue)) {
					String strConvert = com.openapi.framework.utils.StringUtil.htmlSpecialChars(fieldValue);
					
					setMethod.invoke(obj, strConvert);
				}
				
				/**
				 * isOnlyNumber
				 */
				if (isOnlyNumber && !"".equals(fieldValue)) {
					try {
						Long.parseLong(fieldValue);
						
					} catch (Exception e) {
						throw new FailureException("EC01115", fieldKorNm, fieldValue);
					}
				}
				
				/*****************************************************************
				 * COMMON 체크
				 *****************************************************************/
				if (!"".equals(fieldValue)) {
					/*String 문자열만 체크*/
					if (field.getType().isAssignableFrom(String.class)) {

						/**
						 * 특수문자 & 엔터값 처리
						 */
						String pattern = "";  //`~!@#$%^&*()-_=+\\|[{]};:'\",<.>/?
						
						/*업체상품코드*/
						if ("stylCd".equals(fieldName)) {
							pattern = " _+'\"";
							
						/*상품명*/
						} else if ("goodsNm".equals(fieldName)) {
							pattern = "'\"";
							
						/*홍보문구*/
						} else if ("prTxt".equals(fieldName)) {
							pattern = "`@#$^*|;:?=_<>{}\\'";
							
						/*기타 나머지 모든 문자열*/
						} else {
							pattern = "<>";
						}
						
						if (!"".equals(pattern)) {
							if (com.openapi.framework.utils.StringUtil.isIncludeChar(fieldValue, pattern)) {
								throw new FailureException("EC01111", fieldKorNm, fieldValue, pattern);
							}
						}
						
						/**
						 * 엔터값을 <br/>로 변환
						 */
						String convertStr = StringUtil.replace(StringUtil.replace(fieldValue, "\r", ""), "\n", "<br/>");
						setMethod.invoke(obj, convertStr);
					}
				}
			}
		}
		
		logger.info("*** Annotation field check end...");
	}
	
	/**
	 * 해당 상품이 현재 접속한 거래선의 상품인지 체크
	 * @param goodsNo : 상품번호
	 * @throws Exception
	 */
	public void checkEnterpriseGoods(Long goodsNo) throws Exception {
		this.checkEmpty(goodsNo, "상품번호");
		
	}
	
	/**
	 * 조회기간값(시작일자, 종료일자)이 존재할 때 해당 값들의 정합성 유무를 체크한다.
	 * <ul>
	 * <li>날짜포맷 체크 / 시작일이 종료일보다 큰지 체크 / isCheckTerm이 true일 때 해당 날짜보다 기간이 큰지 체크</li>
	 * </ul>
	 * @param sdate : 시작일자 (yyyyMMdd)
	 * @param edate : 종료일자 (yyyyMMdd)
	 * @param datePrefixNm : 시작일자앞에 붙는 명 (ex : "주문"일 경우 주문 시작일)
	 * @param datePrefixNm : 종료일자앞에 붙는 명 (ex : "주문"일 경우 주문 종료일)
	 * @param isCheckTerm : 조회기간 체크 유무
	 * @param termType : "M (Month)" or "D (Day)"
	 * @param term : 기간 (int)
	 * @throws Exception
	 */
	public void checkSrchDateBetween(
		String sdate,
		String edate,
		String datePrefixNm,
		boolean isCheckTerm,
		String termType,
		int term
	) throws Exception {
		
		/**
		 * 날짜 포맷 체크
		 */
		if (!"".equals(StringUtil.trimToEmpty(sdate))) {
			if (!DateUtils.isDateFormat(sdate, "yyyyMMdd")) {
				throw new FailureException("EC01106", "기간검색 - " + datePrefixNm + " 시작일자", StringUtil.trimToEmpty(sdate), "yyyyMMdd");
			}
		}
		
		if (!"".equals(StringUtil.trimToEmpty(edate))) {
			if (!DateUtils.isDateFormat(edate, "yyyyMMdd")) {
				throw new FailureException("EC01106", "기간검색 - " + datePrefixNm + " 종료일자", StringUtil.trimToEmpty(edate), "yyyyMMdd");
			}
		}
		
		/**
		 * 시작일 > 종료일 체크
		 */
		if (!DateUtils.isValidDateBetween(sdate, edate)) {
			throw new FailureException("EC20109", StringUtil.trimToEmpty(sdate), StringUtil.trimToEmpty(edate));
		}
		
		/**
		 * 검색기간 체크
		 */
		if (isCheckTerm) {
			if (!"".equals(StringUtil.trimToEmpty(sdate)) && !"".equals(StringUtil.trimToEmpty(edate))) {
				/*월(Month)로 체크*/
				if ("M".equals(StringUtil.trimToEmpty(termType))) {
					if (Long.parseLong(DateUtils.getAddMonth(sdate, term)) < Long.parseLong(edate)) {
						throw new FailureException("EC20110", term, sdate, edate);
					}
					
				}
				/*일(Day)로 체크*/
				else if ("D".equals(StringUtil.trimToEmpty(termType))) {
					if (Long.parseLong(DateUtils.getAddMonth(sdate, term)) < Long.parseLong(edate)) {
						throw new FailureException("EC20111", term, sdate, edate);
					}
				}
			}
		}
	}
}
