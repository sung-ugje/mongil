/**
 * Base Constants
 */
package com.openapi.framework.constants;


public class MgConstants {
	
	/**
	 * Constructor
	 */
	private MgConstants() {}
	
	/** 서버 시작 시간 */
	public static long SERVER_START_TIME;
	
	/** 줄바꿈 */
	public static final String CRLF = "\r\n";
	
	/**************************************************************
	 * 사용유무
	 **************************************************************/
	/** 사용유무 : Y */
	public static final String	FLAG_Y = "Y";
	/** 사용유무 : N */
	public static final String	FLAG_N = "N";
	/** 사용유무명 : 사용 */
	public static final String	FLAG_Y_NM = "사용";
	/** 사용유무명 : 사용안함 */
	public static final String	FLAG_N_NM = "사용안함";
	
	/**************************************************************
	 * 승인처리코드
	 **************************************************************/
	/** 승인처리코드 - 01:승인대기 */
	public static final String CONF_STAT_CD_STBY = "01";
	/** 승인처리코드 - 02:승인완료 */
	public static final String CONF_STAT_CD_FIN = "02";
	/** 승인처리코드 - 03:승인반려 */
	public static final String CONF_STAT_CD_RTRN = "03";
	
	/**************************************************************
	 * 메일발송
	 **************************************************************/
	/**메일 발송 시스템 번호*/
	public static final Integer SYSTEM_USER_NO = 999999;
	
	/** 메일 전송유형 코드(CG_MM011) : 마케팅 - 01 */
	public final static String MAIL_TRNS_KIND_CD_MARKETING = "01";
	/** 메일 전송유형 코드(CG_MM011) : 시스템 - 02 */
	public static final String MAIL_TRNS_KIND_CD_SYSTEM = "02";
	
	/**메일 유형 코드 (CG_MM058) : 오픈API 시스템 메일*/
	public static final String MAIL_SEND_TYPE_CD_SYSTEM = "43";
	
	/** 메일 전송 상태 코드(CG_MM010) : 대기 - 01 */
	public final static String MAIL_SEND_STATUS_CD_WAIT = "01";
	/** 메일 전송 상태 코드(CG_MM010) : 완료 - 02 */
	public final static String MAIL_SEND_STATUS_CD_FINISH = "02";
	/** 메일 전송 상태 코드(CG_MM010) : 실패 - 03 */
	public final static String MAIL_SEND_STATUS_CD_FAIL = "03";
}
