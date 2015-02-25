/**
 * ValueObject Utils
 */
package com.openapi.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openapi.domain.common.BaseDomain;
import com.openapi.framework.constants.MgConstants;


public class ValueObjectUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ValueObjectUtils.class);
	private static StringBuilder sb = new StringBuilder();

	/**
	 * Constructor
	 */
	private ValueObjectUtils() {}

	/**
	 * List 객체에 담긴 VO를 꺼내어 반환한다.
	 * @param list
	 * @return
	 */
	public static BaseDomain getValueObjectFromList(List<?> list) {
		BaseDomain vo = null;

		if (list != null && list.size() > 0) {
			vo = (BaseDomain) list.get(0);
		}

		return vo;
	}
	
	/**
	 * 조회된 전체목록 갯수를 List 객체에 담는다.
	 * @param list
	 * @param totalRecordCount
	 */
	public static void setTotalRecordCount(List<?> list, int totalRecordCount) {
		logger.debug("totalRecordCount -> " + totalRecordCount);
		
		if (list != null && list.size() > 0) {
			for (int i=0; i<list.size(); i++) {
				if (i > 0) break;
				
				BaseDomain rsvo = (BaseDomain) list.get(i);
				rsvo.setTotalRecordCount(totalRecordCount);
			}
		}
	}

	/**
	 * 리스트목록에서 페이징처리에 필요한 전체 ROWS 갯수를 구한다.
	 * @param list
	 * @return
	 */
	public static Integer getTotalRecordCount(List<?> list) {
		int iResult = 0;

		if (list != null && list.size() > 0) {
			BaseDomain vo = (BaseDomain)list.get(0);
			iResult = vo.getTotalRecordCount();
		}

		logger.debug("totalRecordCount -> " + iResult);

		return iResult;
	}

	/**
	 * 페이징처리를 위한 전체페이지수를 구한다.
	 * @param totalRowCount
	 * @param rowsPerPage
	 * @return
	 */
	public static Integer getTotalPages(Integer totalRowCount, Integer rowsPerPage) {
		int totalPages = (int) Math.ceil((double) totalRowCount / (double) rowsPerPage);
		
		logger.debug("total pages -> " + totalPages);

		return totalPages;
	}

	/**
	 * 한글 초성검색시 필요한 쿼리문를 조합하여 구한다.
	 * @param str
	 * @return
	 */
	public static String getKorChosungSearchKeyword(String str, String columnName) {
		String strResult = "";

		str = StringUtil.trimToEmpty(str);

		if ("ㄱ".equals(str)) {
			strResult = "(" + columnName + " >= '가' AND " + columnName + " < '나')";
		} else if ("ㄴ".equals(str)) {
			strResult = "(" + columnName + " >= '나' AND " + columnName + " < '다')";
		} else if ("ㄷ".equals(str)) {
			strResult = "(" + columnName + " >= '다' AND " + columnName + " < '라')";
		} else if ("ㄹ".equals(str)) {
			strResult = "(" + columnName + " >= '라' AND " + columnName + " < '마')";
		} else if ("ㅁ".equals(str)) {
			strResult = "(" + columnName + " >= '마' AND " + columnName + " < '바')";
		} else if ("ㅂ".equals(str)) {
			strResult = "(" + columnName + " >= '바' AND " + columnName + " < '사')";
		} else if ("ㅅ".equals(str)) {
			strResult = "(" + columnName + " >= '사' AND " + columnName + " < '아')";
		} else if ("ㅇ".equals(str)) {
			strResult = "(" + columnName + " >= '아' AND " + columnName + " < '자')";
		} else if ("ㅈ".equals(str)) {
			strResult = "(" + columnName + " >= '자' AND " + columnName + " < '차')";
		} else if ("ㅊ".equals(str)) {
			strResult = "(" + columnName + " >= '차' AND " + columnName + " < '카')";
		} else if ("ㅋ".equals(str)) {
			strResult = "(" + columnName + " >= '카' AND " + columnName + " < '타')";
		} else if ("ㅌ".equals(str)) {
			strResult = "(" + columnName + " >= '타' AND " + columnName + " < '파')";
		} else if ("ㅍ".equals(str)) {
			strResult = "(" + columnName + " >= '파' AND " + columnName + " < '하')";
		} else if ("ㅎ".equals(str)) {
			strResult = "(" + columnName + " >= '하')";
		}

		//logger.debug("strResult -> " + strResult);

		return strResult;
	}
	
	/**
	 * JSON 데이터를 VO에 맵핑하는 과정에 필드들 중 java.util.List로 된 필드를<br/>
	 * 명시된 제네릭타입으로 변환하여 다시 해당 List를 setter를 이용해 담아준다.
	 * 
	 * @param obj : VO
	 * @return
	 */
	public static Object setCastingJsonData(Object obj) {
		if (obj == null) {
			return obj;
		}
		
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			
			//logger.info("*** ValueObjectUtils : setCastingJsonData() start...");
			
			if (fields != null && fields.length > 0) {
				for (int i=0; i<fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					
					String fieldName = StringUtil.trimToEmpty(field.getName());
					Object objVal = field.get(obj);
					String fieldValue = "";
					
					if (objVal != null) {
						fieldValue = URLDecoder.decode(StringUtil.trimToEmpty(objVal.toString()), "UTF-8");
					}
					
					if ("".equals(StringUtil.trimToEmpty(fieldValue))) {
						continue;
					}
					
					//logger.debug("{} : {}", new String[]{fieldName, fieldValue});
					
					String fieldTypeName = StringUtil.trimToEmpty(field.getType().getCanonicalName());
					String fieldGenericTypeName = StringUtil.trimToEmpty(field.getGenericType().toString());
					fieldGenericTypeName = StringUtil.replace(StringUtil.substring(fieldGenericTypeName, fieldGenericTypeName.indexOf("<")+1), ">", "");
					
					sb.setLength(0);
					sb.append("------------------------------------------------" + MgConstants.CRLF);
					sb.append("fieldTypeName : " + fieldTypeName + MgConstants.CRLF);
					sb.append("fieldGenericTypeName : " + fieldGenericTypeName + MgConstants.CRLF);
					
					/*jsonData Binding*/
					if (field.getType().isAssignableFrom(List.class)) {
						String getMethodName = "get" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						String setMethodName = "set" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						Method getMethod = obj.getClass().getMethod(getMethodName, new Class[]{});
						Method setMethod = obj.getClass().getMethod(setMethodName, new Class[]{field.getType()});
						
						sb.append("getMethodName : " + getMethodName + MgConstants.CRLF);
						sb.append("setMethodName : " + setMethodName + MgConstants.CRLF);
						
						setMethod.invoke(obj, JSONArray.toCollection(JSONArray.fromObject(getMethod.invoke(obj)), Class.forName(fieldGenericTypeName)));
					}
				}
			}
			
			//logger.debug(OAConstants.CRLF + sb.toString());
			
			//logger.info("*** ValueObjectUtils : setCastingJsonData() end...");
			
		} catch (Exception e) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
		}
		
		return obj;
	}
	
	/**
	 * Encoding 되어진 JSON 데이터를 Decoding 해당 VO에 담는다.
	 * @param list
	 * @return
	 */
	public static List<?> setDecodingData(List<?> list) {
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				ValueObjectUtils.setDecodingData(obj);
			}
		}
		
		return list;
	}
	
	/**
	 * Encoding 되어진 JSON String 데이터를 Decoding 해당 VO에 담는다.
	 * 
	 * @param obj : VO
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Object setDecodingData(Object obj) {
		if (obj == null) {
			return obj;
		}
		
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			
			//logger.info("*** ValueObjectUtils : setDecodingData() start...");
			
			if (fields != null && fields.length > 0) {
				for (int i=0; i<fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					
					String fieldName = StringUtil.trimToEmpty(field.getName());
					Object objVal = field.get(obj);
					String fieldValue = "";
					
					if (objVal != null) {
						fieldValue = URLDecoder.decode(StringUtil.trimToEmpty(objVal.toString()), "UTF-8");
					}
					
					if ("".equals(StringUtil.trimToEmpty(fieldValue))) {
						continue;
					}
					
					//logger.debug("{} : {}", new String[]{fieldName, fieldValue});
					
					String fieldTypeName = StringUtil.trimToEmpty(field.getType().getCanonicalName());
					String fieldGenericTypeName = StringUtil.trimToEmpty(field.getGenericType().toString());
					fieldGenericTypeName = StringUtil.replace(StringUtil.substring(fieldGenericTypeName, fieldGenericTypeName.indexOf("<")+1), ">", "");
					
					sb.setLength(0);
					sb.append("------------------------------------------------" + MgConstants.CRLF);
					sb.append("fieldTypeName : " + fieldTypeName + MgConstants.CRLF);
					sb.append("fieldGenericTypeName : " + fieldGenericTypeName + MgConstants.CRLF);
					
					if (field.getType().isAssignableFrom(String.class)) {
						String getMethodName = "get" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						String setMethodName = "set" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						
						Method getMethod = obj.getClass().getMethod(getMethodName, new Class[]{});
						Method setMethod = obj.getClass().getMethod(setMethodName, new Class[]{field.getType()});
						
						sb.append("getMethodName : " + getMethodName + MgConstants.CRLF);
						sb.append("setMethodName : " + setMethodName + MgConstants.CRLF);
						
						setMethod.invoke(obj, fieldValue);
					}
				}
			}
			
		} catch (Exception e) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
		}
		
		return obj;
	}
	
	/**
	 * VO에 담긴 String 필드값들을 Encoding 시킨다.
	 * @param list
	 * @return
	 */
	public static List<?> setEncodingData(List<?> list) {
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				ValueObjectUtils.setEncodingData(obj);
			}
		}
		
		return list;
	}
	
	/**
	 * VO에 담긴 String 필드값들을 Encoding 시킨다.
	 * 
	 * @param obj : VO
	 * @return
	 */
	@SuppressWarnings("unused")
	public static Object setEncodingData(Object obj) {
		if (obj == null) {
			return obj;
		}
		
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			
			//logger.info("*** ValueObjectUtils : setEncodingData() start...");
			
			if (fields != null && fields.length > 0) {
				for (int i=0; i<fields.length; i++) {
					Field field = fields[i];
					field.setAccessible(true);
					
					String fieldName = StringUtil.trimToEmpty(field.getName());
					Object objVal = field.get(obj);
					String fieldValue = "";
					
					if (objVal != null) {
						fieldValue = URLEncoder.encode(StringUtil.trimToEmpty(objVal.toString()), "UTF-8");
					}
					
					if ("".equals(StringUtil.trimToEmpty(fieldValue))) {
						continue;
					}
					
					//logger.debug("{} : {}", new String[]{fieldName, fieldValue});
					
					String fieldTypeName = StringUtil.trimToEmpty(field.getType().getCanonicalName());
					String fieldGenericTypeName = StringUtil.trimToEmpty(field.getGenericType().toString());
					fieldGenericTypeName = StringUtil.replace(StringUtil.substring(fieldGenericTypeName, fieldGenericTypeName.indexOf("<")+1), ">", "");
					
					sb.setLength(0);
					sb.append("------------------------------------------------" + MgConstants.CRLF);
					sb.append("fieldTypeName : " + fieldTypeName + MgConstants.CRLF);
					sb.append("fieldGenericTypeName : " + fieldGenericTypeName + MgConstants.CRLF);
					
					if (field.getType().isAssignableFrom(String.class)) {
						String getMethodName = "get" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						String setMethodName = "set" + StringUtil.upperCase(StringUtil.substring(fieldName, 0, 1)) + StringUtil.substring(fieldName, 1);
						
						Method getMethod = obj.getClass().getMethod(getMethodName, new Class[]{});
						Method setMethod = obj.getClass().getMethod(setMethodName, new Class[]{field.getType()});
						
						sb.append("getMethodName : " + getMethodName + MgConstants.CRLF);
						sb.append("setMethodName : " + setMethodName + MgConstants.CRLF);
						
						setMethod.invoke(obj, fieldValue);
					}
				}
			}
			
			//logger.debug(OAConstants.CRLF + sb.toString());
			
			//logger.info("*** ValueObjectUtils : setEncodingData() end...");
			
		} catch (Exception e) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
		}
		
		return obj;
	}
}
