/**
 * VO의 멤버변수 Validation을 위한 Custom Annotation
 */
package com.openapi.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * <h3>Field 의 유효성을 검증한다.</h3>
 * <p>아래 항목들에 대하여 필드 값에 대한 유효성을 검증한다.<br>
 * 필드의 값이 null 인 경우는 성공을 반환한다.(notEmpty 제외)<br>
 * 필드의 타입은 기본적으로 모든 타이벵 대하여 기본적으로 체크한다.
 * 유효성 체크의 타입은 아래와 같다.
 * <ul>
 * <li>fieldKorNm : 필드에 정의된 한글 필드명</li>
 * <li>isNotEmpty : true 인 경우 필드값이 필드의 유형에서 유효한 값을 가져야한다.</li>
 * <li>minLength : 지정된 값보다 크거나 같은 길이를 가져야한다.</li>
 * <li>maxLength : 지정된 값보다 작거나 같은 길이를 가져야한다.</li>
 * <li>minValue : 날짜형과 숫자형필드에 대하여 지정한 값보다 크거나 같은 값을 가져야한다.<br>
 * 지정되는 값은 필드와 동일한 유형으로 캐스팅 되어야하고 날짜형 인 경우 "yyyyMMddHHmmss" 유형의 포멧을 따라야한다.</li>
 * <li>minValue : 날짜형과 숫자형필드에 대하여 지정한 값보다 작거나 같은 값을 가져야햔다.<br>
 * 지정되는 값은 필드와 동일한 유형으로 캐스팅 되어야하고 날짜형 인 경우 "yyyyMMddHHmmss" 유형의 포멧을 따라야한다.</li>
 * <li>code : 지정한 유형에 따라 허용되는 값을 가져야한다.</li>
 * <ul>
 * <li> YN : 길이가 1 바이트이며 "Y" 나 "N" 둘중 한가지 값을 가져야 한다.</li>
 * <li> LIST:***,***,*** : "," 로 구분된 코드 리스트 중 하나의 값이어야 한다.<br/>
 * (ex : LIST:123,456,789)</li>
 * <li> CG_**** : 공통코드 테이블에 지정한 그룹의 코드 리스트 중 하나의 값이어야 한다.</li>
 * </ul>
 * </li>
 * <li>dateFormat : 지정된 Format 와 필드값의 유형({@link java.text.SimpleDateFormat SimpleDateFormat} 유형)이 일치하여야 하고 유효한 날짜 값이어야한다.</li> 
 * <li>numberScale : 소숫점을 포함하여 지정된 수치의 자릿수(오라클 지정방식과 동일)여야한다.</li>
 * <li>startValue : 문자열이 반드시 지정된 문자열로 시작되어야한다.</li>
 * <li>defaultValue : 문자열이 없거나 NULL이면 지정된 문자열로 셋팅한다.</li>
 * <li>isBanWord : true 인 경우 DB에 등록된 금칙어를 포함하지 말아야한다.</li>
 * <li>isHtmlSpecialChars : true 인 경우 {@link com.openapi.framework.utils.StringUtil}.htmlSpecialChar()로 문자열을 치환한다.</li>
 * <li>isOnlyNumber : true 인 경우 값이 숫자값이어야만 한다.</li>
 * </ul>
 * </p>
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldCheck {
	
	String fieldKorNm() default "";
	boolean isNotEmpty() default false;
	int minLength() default 0;
	int maxLength() default 0;
	String minValue() default "";
	String maxValue() default "";
	String code() default "";
	String dateFormat() default "";
	double numberScale() default 0.0;
	String startValue() default "";
	String defaultValue() default "";
	boolean isBanWord() default false;
	boolean isHtmlSpecialChars() default false;
	boolean isOnlyNumber() default false;
}
