/**
 * String 유틸
 */
package com.openapi.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.openapi.framework.annotation.FieldCheck;
import com.openapi.framework.constants.MgConstants;


public class StringUtil extends StringUtils{
	
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	private static StringBuilder sb = new StringBuilder();

	/**
	 * Constructor
	 */
	private StringUtil() {}

	/**
	 * 문자열 자리수만큼 끊어주기
	 * @param str 문자열
	 * @param i 자리수
	 * @return String
	 */
	public static String crop(String str, int i) {
		if (str == null) return "";

		return (str.length() > i) ? str.substring(0, i) + "..." : str;
	}

	/**
	 * cropByte
	 * @param str : 원본 문자열
	 * @param i : 몇자까지 자를지의 size
	 * @param trail : '...' 처럼 꼬리에 붙여질 문자열
	 */
	public static String cropByte(String str, int i, String trail) {
		if (str == null) return "";

		String tmp = str;
		int slen = 0, blen = 0;
		char c;

		try {
			if (tmp.getBytes("UTF-8").length > i) {
				while (blen + 1 < i) {
					c = tmp.charAt(slen);
					slen++;

					if (c > 127) {
						blen = blen + 3;  // 오라클 UTF-8 -> 3바이트
					} else {
						blen++;
					}
				}

				tmp = tmp.substring(0, slen) + trail;
			}

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}

		return tmp;
	}

	/**
	 * 입력받은 String을 원하는 길이만큼 원하는 문자로 오른쪽을 채워주는 함수
	 * @param String str
	 * @param int len
	 * @param char pad
	 * @return String value 를 반환한다.
	 */
	public static String rPad(String str, int len, char pad) {
		String result = str;
		int templen = len - result.getBytes().length;

		for (int i = 0; i < templen; i++) {
			result = result + pad;
		}

		return result;
	}

	/**
	 * 입력받은 String을 원하는 길이만큼 원하는 문자로 왼쪽을 채워주는 함수
	 * @param String str
	 * @param int len
	 * @param char pad
	 * @return String value 를 반환한다.
	 */
	public static String lPad(String str, int len, char pad) {
		String result = str;
		int templen = len - result.getBytes().length;

		for (int i = 0; i < templen; i++) {
			result = pad + result;
		}

		return result;
	}

	/**
	 * stringToFloat 문자열을 소수점 dp 이하 자릿숫만큼 float으로 변경
	 * @param str 문자열
	 * @param dp 소수점 자릿수
	 * @return float
	 */
	public static float stringToFloat(String str, int dp) {
		float ft;

		if (str == null || str.equals("")) {
			ft = 0;

		} else {
			if (str.indexOf('.') > 0) {
				String strInt = str.substring(0, str.indexOf('.'));
				String strDp = str.substring(str.indexOf('.') + 1, str.length());

				if (strDp.length() > dp) {
					strDp = strDp.substring(0, dp);
				} else {
					strDp = strDp.substring(0, strDp.length());
				}

				str = strInt + "." + strDp;
			}

			ft = Float.parseFloat(str);
		}

		return ft;
	}

	/**
	 * java.lang.String 패키지의 substring() 메소드와 기능은 동일한데 오른쪽 문자끝부터 count 를 해서 자름
	 * @param str 전체 문자열
	 * @param count 오른쪽 문자끝(1) 부터 count 까지
	 * @return result substring 된 문자열을 반환함
	 */
	public static String rightSubstring(String str, int count) {
		if (str == null) return null;

		String result = null;

		try {
			if (count == 0) {  // 갯수가 0 이면 공백을
				result = "";
			} else if (count > str.length()) {  // 문자열 길이보다 크면 문자열 전체를
				result = str;
			} else {
				result = str.substring(str.length() - count, str.length()); // 오른쪽 count 만큼 리턴
			}

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
			return str;
		}

		return result;
	}

	/**
	 * toKorean 8859_1 에서 EUC-KR 로 문자세트변환
	 * @param str 바꾸려는 문자열
	 * @return String
	 */
	public static String toKorean(String str) {
		if (str == null) return null;

		try {
			return new String(str.getBytes("8859_1"), "EUC-KR");

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
			return str;
		}
	}

	/**
	 * toUnicode EUC-KR 에서 8859_1 로 문자세트변환
	 * @param str 바꾸려는 문자열
	 * @return String
	 */
	public static String toUnicode(String str) {
		if (str == null) return null;

		try {
			return new String(str.getBytes("EUC-KR"), "8859_1");

		} catch (UnsupportedEncodingException e) {
			StringUtil.printExceptionTrace(e);
			return str;
		}
	}

	/**
	 * 문자열의 CharSet을 EUC-KR(KSC5601)로 바꾸는 메서드.
	 * @param source 원본 문자열
	 * @return result EUC-KR로 변환된 문자열
	 */
	public static String toEuckr(String source) {
		if (source == null) return null;

		try {
			return new String(source.getBytes(), "KSC5601");

		} catch (UnsupportedEncodingException e) {
			StringUtil.printExceptionTrace(e);
			return source;
		}
	}

	/**
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 메서드.
	 * @param source 원본 문자열
	 * @param charset CharSet
	 * @return result 지정 charset으로 변환된 문자열
	 */
	public static String toCharset(String source, String charset) {
		if (source == null) return null;

		try {
			return new String(source.getBytes("8859_1"), charset);

		} catch (UnsupportedEncodingException e) {
			StringUtil.printExceptionTrace(e);
			return source;
		}
	}

	/**
	 * 넘겨받은 문자열의 한글 문자열 포함 여부를 체크하는 메서드
	 * @param uni20 체크할 문자열
	 * @return check 한글 문자열 포함 여부
	 */
	public static boolean checkKorean(String uni20) {
		boolean check = false;

		if (uni20 == null || uni20.equals("")) {
			return check;

		} else {
			int len = uni20.length();
			char c;

			for (int i = 0; i < len; i++) {
				c = uni20.charAt(i);

				if (c < 0xac00 || 0xd7a3 < c) {
					check = false;

				} else {
					check = true;
					break;
				}
			}
		}

		return check;
	}

	/**
	 * 숫자형 스트링을 Locale.KOREA 의 숫자형식으로 표시한다.(콤마표시)
	 * @param String : number
	 * @return
	 */
	public static String formatComma(String number) {
		return formatComma(Double.parseDouble(number));
	}

	/**
	 * double형 숫자를 Locale.KOREA 의 숫자형식으로 표시한다.(콤마표시)
	 * @param double : number
	 * @return
	 */
	public static String formatComma(double number) {
		return formatComma(number, Locale.KOREA);
	}

	/**
	 * 숫자형 스트링을 locale 의 숫자형식으로 표시한다.(콤마표시)
	 * @param String : number
	 * @param Locale : locale
	 * @return
	 */
	public static String formatComma(String number, Locale locale) {
		return formatComma(Double.parseDouble(number), locale);
	}

	/**
	 * double형 숫자를 locale 의 숫자형식으로 표시한다.(콤마표시)
	 * @param double : number
	 * @param Locale : locale
	 * @return
	 */
	public static String formatComma(double number, Locale locale) {
		NumberFormat form = NumberFormat.getInstance(locale);
		return form.format(number);
	}

	/**
	 * Double형 타입을 pattern 에 맞게 나타나게 한다. (예 : #,##0.00 )
	 * @param double : obj
	 * @param pattern
	 * @return pattern 의 형식으로 리턴한다.
	 */
	public static String formatDecimal(double obj, String pattern) {
		DecimalFormat form = new DecimalFormat(pattern);
		return form.format(obj);
	}

	/**
	 * 자리수만큼 0을 채워준다.
	 * @param length - 자리수
	 * @param contents - 내용
	 * @return
	 */
	public static String fixNumericLength(int length, String contents) {
		if (contents == null) contents = "";

		StringBuffer sb = new StringBuffer();
		int gap = length - contents.getBytes().length;

		for (int i = 0; i < gap; i++) {
			sb.append("0");
		}

		return sb.append(contents).toString();
	}

	/**
	 * 문자열을 SHA-256 암호화한다.
	 * @param String str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptSha256(String str) {
		String result = "";
		
		str = org.apache.commons.lang.StringUtils.trimToEmpty(str);
		
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA-256");

			md.update(str.getBytes());
			byte[] digest = md.digest();
			
			result = encode(digest);
			
			/*StringBuffer sb = new StringBuffer(); 

			for (int i=0; i<digest.length ; i++) {
				sb.append(Integer.toString((digest[i]&0xff) + 0x100, 16).substring(1));
			}
			
			result = sb.toString();*/
		
		} catch (NoSuchAlgorithmException nsae) {
			StringUtil.printExceptionTrace(nsae);
		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}

		return result;
	}

	/**
	 * 문자열을 MD5 암호화 한다.
	 * @param String str : 암호화할 문자열
	 * @return MD5로 암호화된 문자열
	 */
	public static String encryptMD5(String str) {
		String result = "";
		
		str = org.apache.commons.lang3.StringUtils.trimToEmpty(str);
		
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");

			byte[] digest = md.digest(str.getBytes());
			result = encode(digest);
		
		} catch (NoSuchAlgorithmException nsae) {
			StringUtil.printExceptionTrace(nsae);
		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}

		return result;
	}

	/**
	 * Base64Encoding을 수행한다. (binany in,  ascii out)
	 * @param byte[] encodeBytes : encoding할 byte array
	 * @return encoding 된 String
	 */
	public static String encode(byte[] encodeBytes) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		
		ByteArrayInputStream bin = new ByteArrayInputStream(encodeBytes);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		byte[] buf = null;

		try {
			base64Encoder.encodeBuffer(bin, bout);

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		} finally {
			IOUtils.closeQuietly(bin);
			IOUtils.closeQuietly(bout);
		}

		buf = bout.toByteArray();

		return org.apache.commons.lang3.StringUtils.trimToEmpty(new String(buf));
	}

	/**
	 * Base64Decoding 수행한다. (ascii in, binany out)
	 * @param strDecode decoding할 String
	 * @return decoding 된 byte array
	 */
	public static byte[] decode(String strDecode) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		
		ByteArrayInputStream bin = new ByteArrayInputStream(strDecode.getBytes());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		byte[] buf = null;

		try {
			base64Decoder.decodeBuffer(bin, bout);

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
			return null;
		} finally {
			IOUtils.closeQuietly(bin);
			IOUtils.closeQuietly(bout);
		}

		buf = bout.toByteArray();

		return buf;
	}

	/**
	 * 바이트를 구간별로 자른다.
	 * @param bytes
	 * @param start
	 * @param end
	 * @return byte[] : 구간별로 잘려진 바이트배열
	 */
	public static byte[] subByte(byte[] bytes, int start, int end) {
		byte[] returnBytes = new byte[end - start];

		try {
			int index = 0;

			for (int i = start; i < end; i++) {
				returnBytes[index++] = bytes[i];
			}

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}

		return returnBytes;
	}

	/**
	 * 엔터값 변환
	 * @param src
	 * @return String : br 태그로 변환된 스트링문자열
	 */
	public static String nl2br(String src) {
		String result = org.apache.commons.lang3.StringUtils.trimToEmpty(src);

		try {
			if (!"".equals(result)) {
				result = org.apache.commons.lang3.StringUtils.replace(result, "\r\n", "<br/>");
				result = org.apache.commons.lang3.StringUtils.replace(result, "\n", "<br/>");
			}

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
			return src;
		}

		return result;
	}
	
	/**
	 * <br/>태그를 엔터값으로 변경
	 * @param src
	 * @return
	 */
	public static String br2nl(String src) {
		String result = StringUtils.trimToEmpty(src);
		
		try {
			if (!"".equals(result)) { 
				result = StringUtils.replace(result, "<BR/>", "\n");
				result = StringUtils.replace(result, "<br/>", "\n");
				result = StringUtils.replace(result, "<BR>", "\n");
				result = StringUtils.replace(result, "<br>", "\n");
			}

		} catch (Exception e) {
			StringUtil.printExceptionTrace(e);
		}

		return result;
	}

	/**
	 * HTML 특수문자 처리
	 * @param str
	 * @return
	 */
	public static String htmlSpecialChars(String str) {
		str = StringUtils.trimToEmpty(str);
		
		sb.setLength(0);
		
		for (int i=0; i<str.length(); i++) {
			String subStr = StringUtils.substring(str, i, (i+1));
			
			if ("<".equals(subStr)) {
				sb.append("&lt;");
				
			} else if (">".equals(subStr)) {
				sb.append("&gt;");
				
			} else if ("\"".equals(subStr)) {
				sb.append("&quot;");
				
			} else if ("&".equals(subStr)) {
				sb.append("&amp;");
				
			} else if ("\\".equals(subStr)) {
				sb.append("&apos;");
				
			} else {
				sb.append(subStr);
			}
		}

		return sb.toString();
	}
	
	/**
	 * 원본 문자열에 해당문자열의 단 하나의 문자라도 포함되는지 여부 확인
	 * @param source : 원본문자열
	 * @param incString : 포함여부 문자열
	 * @return
	 */
	public static boolean isIncludeChar(String source, String incString) {
		source = StringUtils.trimToEmpty(source);
		incString = StringUtils.trimToEmpty(incString);
		
		int j = 0;
		if (!"".equals(incString)) {
			for (int i=0; i<incString.length(); i++) {
				String subStr = StringUtils.substring(incString, i, (i+1));
				
				if (source.indexOf(subStr) >= 0) {
					j++;
					break;
				}
			}
		}
		
		if (j > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * XSS 취약점을 해결하기 위해 문자열 치환
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str) {
		 str = StringUtils.trimToEmpty(str);
		  
		  str = StringUtils.replace(str, "script", "");
		  str = StringUtils.replace(str, "alert(", "");
		  str = StringUtils.replace(str, "confirm(", "");
		  str = StringUtils.replace(str, "prompt(", "");
		  str = StringUtils.replace(str, "javascript", "");
		  str = StringUtils.replace(str, "location.", "");
		  str = StringUtils.replace(str, "document.", "");
		  str = StringUtils.replace(str, "<object", "");
		  str = StringUtils.replace(str, "</object>", "");
		  str = StringUtils.replace(str, "<param", "");
		  str = StringUtils.replace(str, "</param>", "");
		  str = StringUtils.replace(str, "<xml", "");
		  str = StringUtils.replace(str, "</xml>", "");
		  str = StringUtils.replace(str, "=\"refresh\"", "");
		  str = StringUtils.replace(str, "='refresh'", "");
		  str = StringUtils.replace(str, "frameset", "");
		  str = StringUtils.replace(str, "onclick", "");
		  str = StringUtils.replace(str, "onfocus", "");
		  str = StringUtils.replace(str, "onchange", "");
		  str = StringUtils.replace(str, "onscroll", "");
		  str = StringUtils.replace(str, "onmove", "");
		  str = StringUtils.replace(str, "onerror", "");
		  str = StringUtils.replace(str, "applet", "");
		  str = StringUtils.replace(str, "embed", "");
		  str = StringUtils.replace(str, "<form", "");
		  str = StringUtils.replace(str, "</form>", "");
		  str = StringUtils.replace(str, "msgbox(", "");
		  
		  str = StringUtils.replace(str, "SCRIPT", "");
		  str = StringUtils.replace(str, "ALERT(", "");
		  str = StringUtils.replace(str, "CONFIRM(", "");
		  str = StringUtils.replace(str, "PROMPT(", "");
		  str = StringUtils.replace(str, "JAVASCRIPT", "");
		  str = StringUtils.replace(str, "LOCATION.", "");
		  str = StringUtils.replace(str, "DOCUMENT.", "");
		  str = StringUtils.replace(str, "<OBJECT", "");
		  str = StringUtils.replace(str, "</OBJECT>", "");
		  str = StringUtils.replace(str, "<PARAM", "");
		  str = StringUtils.replace(str, "</PARAM>", "");
		  str = StringUtils.replace(str, "<XML", "");
		  str = StringUtils.replace(str, "</XML>", "");
		  str = StringUtils.replace(str, "=\"REFRESH\"", "");
		  str = StringUtils.replace(str, "='REFRESH'", "");
		  str = StringUtils.replace(str, "FRAMESET", "");
		  str = StringUtils.replace(str, "ONCLICK", "");
		  str = StringUtils.replace(str, "ONFOCUS", "");
		  str = StringUtils.replace(str, "ONCHANGE", "");
		  str = StringUtils.replace(str, "ONSCROLL", "");
		  str = StringUtils.replace(str, "ONMOVE", "");
		  str = StringUtils.replace(str, "ONERROR", "");
		  str = StringUtils.replace(str, "APPLET", "");
		  str = StringUtils.replace(str, "EMBED", "");
		  str = StringUtils.replace(str, "<FORM", "");
		  str = StringUtils.replace(str, "</FORM>", "");
		  str = StringUtils.replace(str, "MSGBOX(", "");
		  
		  str = StringUtils.replace(str, "<%", "");
		  str = StringUtils.replace(str, "%>", "");
		  str = StringUtils.replace(str, "<?", "");
		  str = StringUtils.replace(str, "?>", "");
		  str = StringUtils.replace(str, ");", "");
		  str = StringUtils.replace(str, "$.", "");
		  str = StringUtils.replace(str, "$(", "");
		
		return str;
	}

	/**
	 * 한글 초성검색시 필요한 쿼리문를 조합하여 구한다.
	 * @param str
	 * @return
	 */
	public static String getKorChosungSearchKeyword(String str, String columnName) {
		String strResult = "";

		str = StringUtils.trimToEmpty(str);

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
		
		logger.debug("Search Keyword : {}", strResult);

		return strResult;
	}
	
	/**
	 * 날짜형 문자열에 날짜 구분자값을 제거한다.
	 * @param source
	 * @return
	 */
	public static String getDateRemoveDelimeter(String source) {
		source = StringUtils.trimToEmpty(source);
		
		if (!"".equals(source)) {
			source = source.replaceAll("[-/.]", "");
		}
		
		return source;
	}
	
	/**
	 * 특정 모델에 어노테이션으로 선언된 fieldKorNm 값을 구한다.
	 * @param obj : VO
	 * @throws Exception
	 */
	public static String getFieldKorNm(Object obj, String strFieldName) {
		String resString = "";
		
		if (obj == null) {
			return resString;
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		if (fields != null && fields.length > 0) {
			for (int i=0; i<fields.length; i++) {
				Field field = fields[i];
				
				field.setAccessible(true);
				
				String fieldName = StringUtils.trimToEmpty(field.getName());
				
				FieldCheck fieldCheckClass = field.getAnnotation(FieldCheck.class);
				
				if (fieldCheckClass == null) continue;
				
				if (StringUtils.trimToEmpty(strFieldName).equals(fieldName)) {
					String fieldKorNm = StringUtils.trimToEmpty(fieldCheckClass.fieldKorNm());
					
					if ("".equals(fieldKorNm)) {
						fieldKorNm = fieldName;
					} else {
						fieldKorNm = fieldKorNm + " (" + fieldName + ")";
					}
					
					resString = fieldKorNm;
					
					break;
				}
			}
		}
		
		return resString;
	}
	
	/**
	 * 파라미터를 저장한 맵을 구한다.
	 * @param queryString
	 * @return
	 */
	public static Map<String, String> getQueryStringToMap(String queryString) {
		Map<String, String> queryStringMap = new HashMap<String, String>();
		
		if ("".equals(StringUtils.trimToEmpty(queryString))) {
			return null;
		}
		
		String[] queryStrings = StringUtils.split(queryString, "&");
		
		if (queryStrings != null && queryStrings.length > 0) {
			for (int i=0; i<queryStrings.length; i++) {
				String str = StringUtils.trimToEmpty(queryStrings[i]);
				String[] strs = StringUtils.split(str, "=");
				
				if (strs != null && strs.length == 2) {
					sb.setLength(0);
					sb.append("----------------------------------------------------------" + MgConstants.CRLF);
					sb.append("str : " + str + MgConstants.CRLF);
					sb.append("strs[0] : " + StringUtils.trimToEmpty(strs[0]) + MgConstants.CRLF);
					sb.append("strs[1]" + StringUtils.trimToEmpty(strs[1]) + MgConstants.CRLF);
					
					logger.debug(MgConstants.CRLF + sb.toString());
					
					queryStringMap.put(StringUtils.trimToEmpty(strs[0]), StringUtils.trimToEmpty(strs[1]));
				}
			}
		}
		
		return queryStringMap;
	}
	
	/**
	 * Exception의 printStackTrace 내용을 String으로 변환
	 * @param e : Exception
	 * @return
	 */
	public static String getStackTraceToString(Throwable e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(out);
		
		String rsString = "";
		
		try {
			e.printStackTrace(printStream);
			rsString = out.toString();
			
		} catch (Exception e2) {
			//
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(printStream);
		}
		
		return rsString;
	}
	
	/**
	 * 익셉션 printStackTrace() 실행
	 * @param e : Exception
	 */
	public static void printExceptionTrace(Throwable e) {
		logger.error("printStackTrace() start...");
		
		logger.error(StringUtil.getStackTraceToString(e));
		
		logger.error("printStackTrace() end...");
	}
	
	/**
	 * 문자열에 한글이 포함되어 있는지 확인
	 * @param str : 문자열
	 * @return
	 */
	public static boolean isContainKorean(String str) {
		str = StringUtils.trimToEmpty(str);
		
		if ("".equals(str)) {
			return false;
		}
		
		for (int i = 0; i<str.length(); i++) {
	        char ch = str.charAt(i);
	        
	        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);
	        
	        if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock)
	        	|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
	        	|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)
	        ) {
	        	
	        	return true;
	        }
	    }
		
	    return false;
	}
	

	public static String uuid(int len) {
		char[] CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		int radix = CHARS.length;
		char[] uuid = new char[len];
		for (int i = 0; i < len; i++) {
			uuid[i] = CHARS[(int) (Math.random() * radix)];
		}
		return new String(uuid);
	}
 
}
	

