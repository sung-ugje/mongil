/**
 * 에러코드 Constants
 */
package com.openapi.framework.constants;

public class ErrorCodeConstants {
	
	/**
	 * Constructor
	 */
	private ErrorCodeConstants() {}

	/**인증키 유무체크*/
	public static final String EC_TOKEN_REQUIRED = "EC99100";
	
	/**인증여부 체크*/
	public static final String EC_AUTHENTICATION_REQUIRED = "EC99101";
	
	/**세션아이디 유무체크*/
	public static final String EC_SESSIONID_REQUIRED = "EC99102";
	
	/**Request Header에 Accept 설정이 필요합니다.*/
	public static final String EC_NO_ACCEPT = "EC99991";
	
	/**처리중 파라미터 타입 오류 또는 데이터 누락으로 인한 오류가 발생했습니다.*/
	public static final String EC_BAD_REQUEST = "EC99992";
	
	/**지원하지 않는 메소드 요청으로 인한 오류가 발생했습니다.*/
	public static final String EC_METHOD_NOT_ALLOWED = "EC99993";
	
	/**지원하지 않는 Media Type입니다.*/
	public static final String EC_UNSUPPORTED_MEDIA_TYPE = "EC99994";
	
	/**허용할 수 없는 Media Type입니다.*/
	public static final String EC_NOT_ACCEPTABLE_MEDIA_TYPE = "EC99995";
	
	/**해당 자원을 찾을 수 없습니다.*/
	public static final String EC_NOT_FOUND = "EC99996";
	
	/**쿼리실행 도중 오류가 발생했습니다.*/
	public static final String EC_DATA_EXCEPTION = "EC99997";
	
	/**처리중 서버에 오류가 발생했습니다.*/
	public static final String EC_INTERNAL_SERVER_ERROR = "EC99999";
}
