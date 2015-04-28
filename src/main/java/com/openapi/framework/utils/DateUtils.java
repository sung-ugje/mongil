/**
 *
 */
package com.openapi.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openapi.framework.constants.MgConstants;


public class DateUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	private static StringBuilder sb = new StringBuilder();

	/**
	 * Constructor
	 */
	private DateUtils() {}

	/**
	 * 시스템 년도 return
	 * @return String
	 */
	public static String getYear() {
		return getDateByPattern("yyyy");
	}

	/**
	 * 시스템 월 return
	 * @return String
	 */
	public static String getMonth() {
		return getDateByPattern("MM");
	}

	/**
	 * 시스템 일 return
	 * @return String
	 */
	public static String getDay() {
		return getDateByPattern("dd");
	}

	/**
	 * 시스템 시간 return
	 * @return String
	 */
	public static String getHour() {
		return getDateByPattern("HH");
	}

	/**
	 * 시스템 분 return
	 * @return String
	 */
	public static String getMinute() {
		return getDateByPattern("mm");
	}

	/**
	 * 시스템 초 return
	 * @return String
	 */
	public static String getSecond() {
		return getDateByPattern("ss");
	}

	/**
	 * 'yyyyMMdd' 형으로 현재 날짜를 리턴
	 * @return String
	 */
	public static String getShortDate() {
		return getDateByPattern("yyyyMMdd");
	}

	/**
	 * 'yyyy-MM-dd' 형으로 현재 날짜를 리턴
	 * @return String
	 */
	public static String getDate() {
		return getDateByPattern("yyyy-MM-dd");
	}

	/**
	 * 'HHmmss' 형으로 현재 시간을 리턴
	 * @return String
	 */
	public static String getShortTime() {
		return getDateByPattern("HHmmss");
	}

	/**
	 * 'HH:mm:ss' 형으로 현재 시간을 리턴
	 * @return String
	 */
	public static String getTime() {
		return getDateByPattern("HH:mm:ss");
	}

	/**
	 * 지금은 올해 몇번째 주 인지..~
	 * @return String
	 */
	public static String getWeekOfYear() {
		return getDateByPattern("w");
	}

	/**
	 * 지금은 이번달 몇번째 주 인지..~
	 * @return String
	 */
	public static String getWeekOfMonth() {
		return getDateByPattern("W");
	}

	/**
	 * 'yyyy-MM-dd HH:mm:ss' 형으로 현재날짜와 시간을 리턴
	 * @return String
	 */
	public static String getFullDate() {
		return getDateByPattern("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 여러 Pattern 으로 날짜와 시간의 String 값을 리턴
	 * @return String
	 */
	public static String getDateByPattern(String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, Locale.KOREA);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 여러 Pattern / Locale 설정으로 날짜와 시간의 String 값을 리턴
	 * @return String
	 */
	public static String getDateByPattern(String pattern, Locale locale) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, locale);
		return formatter.format(new java.util.Date());
	}
	
	/**
	 * 여러 Pattern 설정으로 해당 날짜와 시간의 Date 값을 리턴
	 * @param date : yyyyMMddHHmmss 형태의 
	 * @param pattern
	 * @return
	 */
	public static Date getDateFromString(String source, String pattern) {
		if (!"".equals(StringUtil.trimToEmpty(source))) {
			Date parsed = null;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			
			try {
				String trimSource = StringUtil.remove(source, "-");
				trimSource = StringUtil.remove(trimSource, "/");
				trimSource = StringUtil.remove(trimSource, ".");
				trimSource = StringUtil.remove(trimSource, ":");
				trimSource = StringUtil.remove(trimSource, " ");
				
				dateFormat.setLenient(false);
				
				parsed = dateFormat.parse(trimSource);
				
				/*if (trimSource.length() == 8) {
					parsed = formatyMd.parse(trimSource);
				} else if (trimSource.length() == 12) {
					parsed = formatyMdhm.parse(trimSource);
				} else if (trimSource.length() == 14) {
					parsed = formatyMdHms.parse(trimSource);
				}*/
				
			} catch (Exception e) {
				/*입력된 문자열이 날짜 형식이 아님*/
				//StringUtil.printExceptionTrace(e);
				return null;
			}
			
			return parsed;
			
		} else {
			return null;
		}
	}
	
	/**
	 * Date형을 입력받아 원하는 패턴으로 리턴
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateByPattern(Date date, String pattern) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern, Locale.KOREA);
		return formatter.format(date);
	}

	/**
	 * 해당년월의 마지막 일자를 구한다.
	 * @param date : yyyyMMdd
	 * @return String
	 */
	public static String getLastDate(String date) {
		Calendar calendar = Calendar.getInstance();

		int yyyy = Integer.parseInt(date.substring(0, 4));
		int MM = Integer.parseInt(date.substring(4, 6));
		int dd = Integer.parseInt(date.substring(6, 8));
		
		sb.setLength(0);
		sb.append("--------------------------------------------" + MgConstants.CRLF);
		sb.append("년 : " + yyyy + MgConstants.CRLF);
		sb.append("월 : " + MM + MgConstants.CRLF);
		sb.append("일 : " + dd + MgConstants.CRLF);
		
		logger.debug(MgConstants.CRLF + sb.toString());

		calendar.set(yyyy, MM - 1, dd);

		return String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < 10 ? "0" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) : calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 해당날짜의 요일을 계산한다. getDayOfWeek("20000225") : 금
	 * @param date yyyyMMdd
	 * @return result 해당 날짜의 요일명 반환
	 */
	public static String getDayOfWeek(String date) {
		if (date == null) return null;

		int yyyy = 0, MM = 1, dd = 1, day_of_week; // default
		String days[] = { "일", "월", "화", "수", "목", "금", "토" };

		try {
			yyyy = Integer.parseInt(date.substring(0, 4));
			MM = Integer.parseInt(date.substring(4, 6));
			dd = Integer.parseInt(date.substring(6, 8));
			
			sb.setLength(0);
			sb.append("--------------------------------------------" + MgConstants.CRLF);
			sb.append("년 : " + yyyy + MgConstants.CRLF);
			sb.append("월 : " + MM + MgConstants.CRLF);
			sb.append("일 : " + dd + MgConstants.CRLF);
			
			logger.debug(MgConstants.CRLF + sb.toString());

		} catch (Exception ex) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(ex);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, MM - 1, dd);
		day_of_week = calendar.get(Calendar.DAY_OF_WEEK); // 1(일),2(월),3(화),4(수),5(목),6(금),7(토)

		return days[day_of_week - 1];
	}

	/**
	 * 해당 날짜의 요일을 계산. getDayOfWeek("20000225") : 6
	 * @param date yyyyMMdd
	 * @return int 해당 날짜의 요일 코드 반환
	 */
	public static int getWeekday(String date) {
		if (date == null) return -1;

		int yyyy = 0, MM = 1, dd = 1, day_of_week; // default

		try {
			yyyy = Integer.parseInt(date.substring(0, 4));
			MM = Integer.parseInt(date.substring(4, 6));
			dd = Integer.parseInt(date.substring(6, 8));

			sb.setLength(0);
			sb.append("--------------------------------------------" + MgConstants.CRLF);
			sb.append("년 : " + yyyy + MgConstants.CRLF);
			sb.append("월 : " + MM + MgConstants.CRLF);
			sb.append("일 : " + dd + MgConstants.CRLF);
			
			logger.debug(MgConstants.CRLF + sb.toString());

		} catch (Exception ex) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(ex);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(yyyy, MM - 1, dd);
		day_of_week = calendar.get(Calendar.DAY_OF_WEEK); // 1(일),2(월),3(화),4(수),5(목),6(금),7(토)

		return day_of_week;
	}

	/**
	 * 해당날짜가 그달의 몇번째 주인지 조회
	 * @param date : yyyyMMdd
	 * @return
	 */
	public static int getWeekOfMonth(String date) {
		Calendar calendar = Calendar.getInstance();

		int yyyy = Integer.parseInt(date.substring(0, 4));
		int MM = Integer.parseInt(date.substring(4, 6));
		int dd = Integer.parseInt(date.substring(6, 8));

		calendar.set(yyyy, MM - 1, dd);
		
		sb.setLength(0);
		sb.append("--------------------------------------------" + MgConstants.CRLF);
		sb.append("년 : " + calendar.get(Calendar.YEAR) + MgConstants.CRLF);
		sb.append("월 : " + (calendar.get(Calendar.MONTH) + 1) + MgConstants.CRLF);
		sb.append("일 : " + calendar.get(Calendar.DATE) + MgConstants.CRLF);
		
		/*
		sb.append("이해의 몇째주  " + calendar.get(Calendar.WEEK_OF_YEAR));
		sb.append("이달의 몇째주 : " + calendar.get(Calendar.WEEK_OF_MONTH));
		sb.append("이달의 몇일 : " + calendar.get(Calendar.DAY_OF_MONTH));
		sb.append("이해의 몇일 : " + calendar.get(Calendar.DAY_OF_YEAR));
		sb.append("이달의 몇째 요일 : " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		sb.append("TimeZone(-12 ~ +12) : " + calendar.get(Calendar.ZONE_OFFSET)/(60*60*1000));
		sb.append("이달의 마지막 일 : " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		sb.append("이달의 전체주 수 : " + calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
		*/
		
		logger.debug(MgConstants.CRLF + sb.toString());

		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 해당날짜가 속한 그달의  전체 주수를 조회
	 * @param date : yyyyMMdd
	 * @return
	 */
	public static int getTotalWeekOfMonth(String date) {
		Calendar calendar = Calendar.getInstance();

		int yyyy = Integer.parseInt(date.substring(0, 4));
		int MM = Integer.parseInt(date.substring(4, 6));
		int dd = Integer.parseInt(date.substring(6, 8));

		sb.setLength(0);
		sb.append("--------------------------------------------" + MgConstants.CRLF);
		sb.append("년 : " + yyyy + MgConstants.CRLF);
		sb.append("월 : " + MM + MgConstants.CRLF);
		sb.append("일 : " + dd + MgConstants.CRLF);
		
		logger.debug(MgConstants.CRLF + sb.toString());

		calendar.set(yyyy, MM - 1, dd);

		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 해당날짜가 속한 주의 시작일과 종료일
	 * @param date : yyyyMMdd
	 * @param type : START or END
	 */
	public static String getDateOfWeek(String date, String type) {
		String dateOfWeek = "";

		int START_OF_WEEK = 1;
		int END_OF_WEEK = 7;

		Calendar sCalendar = Calendar.getInstance();

		int yyyy = Integer.parseInt(date.substring(0, 4));
		int MM = Integer.parseInt(date.substring(4, 6));
		int dd = Integer.parseInt(date.substring(6, 8));
		
		sb.setLength(0);
		sb.append("--------------------------------------------" + MgConstants.CRLF);
		sb.append("년 : " + yyyy + MgConstants.CRLF);
		sb.append("월 : " + MM + MgConstants.CRLF);
		sb.append("일 : " + dd + MgConstants.CRLF);
		
		logger.debug(MgConstants.CRLF + sb.toString());

		sCalendar.set(yyyy, MM - 1, dd);

		Calendar eCalendar = (Calendar)sCalendar.clone();

		int dayOfWeek = sCalendar.get(Calendar.DAY_OF_WEEK);
		sCalendar.add(Calendar.DATE, START_OF_WEEK - dayOfWeek); // 시작요일을 일요일로 지정
		eCalendar.add(Calendar.DATE, END_OF_WEEK - dayOfWeek); // 마지막요일을 토요일로 지정

		if ("START".equalsIgnoreCase(type)) {
			dateOfWeek = ""
				+ sCalendar.get(Calendar.YEAR)
				+ ((sCalendar.get(Calendar.MONTH)+1) < 10 ? "0" + (sCalendar.get(Calendar.MONTH)+1) : (sCalendar.get(Calendar.MONTH)+1))
				+ (sCalendar.get(Calendar.DATE) < 10 ? "0" + sCalendar.get(Calendar.DATE) : sCalendar.get(Calendar.DATE));

		} else if ("END".equalsIgnoreCase(type)) {
			dateOfWeek = ""
				+ eCalendar.get(Calendar.YEAR)
				+ ((eCalendar.get(Calendar.MONTH)+1) < 10 ? "0" + (eCalendar.get(Calendar.MONTH)+1) : (eCalendar.get(Calendar.MONTH)+1))
				+ (eCalendar.get(Calendar.DATE) < 10 ? "0" + eCalendar.get(Calendar.DATE) : eCalendar.get(Calendar.DATE));
		}

		sb.setLength(0);
		sb.append("--------------------------------------------" + MgConstants.CRLF);
		sb.append("start day : " + sCalendar.get(Calendar.DATE) + MgConstants.CRLF);
		sb.append("end day : " + eCalendar.get(Calendar.DATE) + MgConstants.CRLF);
		sb.append("dateOfWeek : " + dateOfWeek + MgConstants.CRLF);
		
		logger.debug(MgConstants.CRLF + sb.toString());

		return dateOfWeek;
	}

	/**
	 * 오늘 날짜의 요일명을 계산한다.
	 * @return 오늘의 요일명을 반환함
	 */
	public static String getDayOfWeek() {
		return getDayOfWeek(getDate());
	}

	/**
	 * AddMonth
	 * @param date yyyyMMdd
	 * @param month param 상대적으로 구할 날짜 (-3 : 3개월전, 1 : 1개월후)
	 * @return String value 를 반환한다.
	 */
	public static String getAddMonth(String date, int month) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 8) return ""; // 최소 8 자리

		return getAddDate(date, "yyyyMMdd", "month", month);
	}

	/**
	 * AddMonth
	 * @param date yyyyMMdd
	 * @param month param 상대적으로 구할 날짜 (-3 : 3개월전, 1 : 1개월후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddMonth(String date, int month, String type) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 8) return ""; // 최소 8 자리

		return getAddDate(date, type, "month", month);
	}

	/**
	 * AddDay
	 * @param date yyyyMMdd
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3일전, 100 : 100일후)
	 * @return String value 를 반환한다.
	 */
	public static String getAddDay(String date, int day) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 8) return ""; // 최소 8 자리

		return getAddDate(date, "yyyyMMdd", "date", day);
	}

	/**
	 * AddDay
	 * @param date yyyyMMdd
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3일전, 100 : 100일후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddDay(String date, int day, String type) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 8) return ""; // 최소 8 자리

		return getAddDate(date, type, "date", day);
	}

	/**
	 * AddHour
	 * @param date yyyyMMddHHmmss
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3시간전, 3 : 3시간후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddHour(String date, int hour) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 14) return ""; // 최소 14 자리

		return getAddDate(date, "yyyyMMddHHmmss", "hour", hour);
	}

	/**
	 * AddHour
	 * @param date yyyyMMddHHmmss
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3시간전, 3 : 3시간후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddHour(String date, int hour, String type) {
		if ("".equals(StringUtil.trimToEmpty(date))) return "";
		
		if (date.length() < 14) return ""; // 최소 14 자리

		return getAddDate(date, type, "hour", hour);
	}

	/**
	 * AddMinute
	 * @param date yyyyMMddHHmmss
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3분전, 10 : 10분후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddMinute(String date, int minute) {
		return getAddDate(date, "yyyyMMddHHmmss", "minute", minute);
	}

	/**
	 * AddMinute
	 * @param date yyyyMMddHHmmss
	 * @param rday param 상대적으로 구할 날짜 (-3 : 3분전, 10 : 10분후)
	 * @param type return 받을 날짜 형식
	 * @return String value 를 반환한다.
	 */
	public static String getAddMinute(String date, int minute, String type) {
		return getAddDate(date, type, "minute", minute);
	}

	/**
	 * 해당 날짜에 년, 월, 일, 시, 분을 더하거나 뺀 날짜를 리턴한다.
	 * @param date yyyyMMddHHmmss
	 * @param type type 패턴에 따라 날짜가 형식이 변환된다.
	 * @param gubn 년, 월, 일, 시, 분 중 하나를 세팅한다. (month, date, hour, minute)
	 * @param rec 빼거나 더할 숫자를 넣는다.
	 * @return 더하거나 뺀 날짜를 리턴
	 */
	public static String getAddDate(String date, String type, String gubn, int rec) {
		String result = "";
		int year = 0, month = 0, day = 0, hour = 0, min = 0, sec = 0;

		if (date == null) return null;
		if (type == null) return null;

		try {
			year = Integer.parseInt(date.substring(0, 4));
			month = Integer.parseInt(date.substring(4, 6)) - 1;
			day = Integer.parseInt(date.substring(6, 8));

			if (date.length() == 14) {
				hour = Integer.parseInt(date.substring(8, 10));
				min = Integer.parseInt(date.substring(10, 12));
				sec = Integer.parseInt(date.substring(12, 14));
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, hour, min, sec);

			if (gubn == "month") {
				calendar.add(Calendar.MONTH, rec);

			} else if (gubn == "date") {
				calendar.add(Calendar.DATE, rec);

			} else if (gubn == "hour") {
				calendar.add(Calendar.HOUR_OF_DAY, rec);

			} else if (gubn == "minute") {
				calendar.add(Calendar.MINUTE, rec);
			}

			result = (new SimpleDateFormat(type)).format(calendar.getTime());

		} catch (Exception e) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
		}

		return result;
	}

	/**
	 * 월 차이의 달수 구함. 예) getIntervalMonth("20010101", "20000501"); 달의 차
	 * @param firstdate yyyyMMdd
	 * @param lastdate yyyyMMdd
	 * @return 더하거나 뺀 월을 리턴
	 */
	public static int getIntervalMonth(String firstdate, String lastdate) {
		return datediff("month", firstdate, lastdate);
	}

	/**
	 * 일짜 차이의 일수. 예) datediff("20010101", "20000501");
	 * @param firstdate yyyyMMdd
	 * @param lastdate yyyyMMdd
	 * @return 더하거나 뺀 일을 리턴
	 */
	public static int getIntervalDay(String firstdate, String lastdate) {
		return datediff("date", firstdate, lastdate);
	}

	/**
	 * 일짜 차이의 일수, 월 차이의 달수 구함. 예) datediff("d", "20000101", "20010501"); 일의 차 - 작은 날이 앞에, datediff("20010101", "20000501"); 달의 차-작은 날이 뒤에
	 * @param gubn 월, 일 중 하나를 세팅한다.(월 = "month", 일 = "date")
	 * @param firstdate yyyyMMdd
	 * @param lastdate yyyyMMdd
	 * @return 더하거나 뺀 월, 일을 리턴
	 */
	public static int datediff(String gubn, String firstdate, String lastdate) {
		int returnValue = 0;
		long temp = 0;
		int year = 0, month = 0, day = 0, year1 = 0, month1 = 0, day1 = 0;
		int year2 = 0, month2 = 0;
		
		if ("".equals(StringUtil.trimToEmpty(firstdate))) {
			return returnValue;
		}
		
		if ("".equals(StringUtil.trimToEmpty(lastdate))) {
			return returnValue;
		}

		try {
			year = Integer.parseInt(firstdate.substring(0, 4));
			month = Integer.parseInt(firstdate.substring(4, 6));
			day = Integer.parseInt(firstdate.substring(6, 8));

			year1 = Integer.parseInt(lastdate.substring(0, 4));
			month1 = Integer.parseInt(lastdate.substring(4, 6));
			day1 = Integer.parseInt(lastdate.substring(6, 8));

			if (gubn.equals("date")) {
				TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
				Calendar calendar = Calendar.getInstance(tz);

				calendar.set((year - 1900), (month - 1), day);

				Calendar cal2 = Calendar.getInstance(tz);
				cal2.set((year1 - 1900), (month1 - 1), day1);

				java.util.Date temp1 = calendar.getTime();
				java.util.Date temp2 = cal2.getTime();

				temp = temp2.getTime() - temp1.getTime();

				if ((temp % 10) < 5)
					temp = temp - (temp % 10);
				else
					temp = temp + (10 - (temp % 10));

				returnValue = (int) (temp / (1000 * 60 * 60 * 24));

				if (returnValue == 0) {
					returnValue = 1;
				}

			} else {
				year2 = (year1 - year) * 12;
				month2 = month1 - month;
				
				returnValue = year2 + month2;
			}

		} catch (Exception e) {
			com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
		}

		return returnValue;
	}

	/**
	 * 초를 입력받아 일 시:분:초 로..
	 * @param second
	 * @return
	 */
	public static String secondToHHmmss(float second) {
		int sec = (int)second;
		int d=0, h=0, m=0, s=0;
		if (sec > 86400) {
			d = sec / 86400;
			sec %= 86400;
		}
		if (sec > 3600) {
			h = sec / 3600;
			sec %= 3600;
		}
		if (sec > 60) {
			m = sec / 60;
			sec %= 60;
		}
		if (sec < 60) {
			s = sec;
		}
		String result = "";
		if (d > 0) result = d + "일";
		result += ((h < 10)? "0" + h : h) + ":" + ((m < 10)? "0" + m : m) + ":" + ((s < 10)? "0" + s : s);
		return result;
	}
	
	/**
	 * 입력받은 문자열이 패턴에 맞는 날짜형태인지 확인
	 * @param str : 날짜 문자열
	 * @param pattern : yyyyMMdd or yyyyMMddHHmmss등의 날짜 패턴
	 * @return
	 */
	public static boolean isDateFormat(String str, String pattern) {
		str = StringUtil.trimToEmpty(str);
		
		if (str.length() != pattern.length()) {
			return false;
		}
		
		Date date = DateUtils.getDateFromString(str, pattern);
		
		if (date == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 시작일자가 종료일자보다 큰지 체크
	 * @param sdate : 시작일자
	 * @param edate : 종료일자
	 * @return
	 */
	public static boolean isValidDateBetween(String sdate, String edate) {
		boolean resBoolean = true;
		
		if (!"".equals(StringUtil.trimToEmpty(sdate)) && !"".equals(StringUtil.trimToEmpty(edate))) {
			if (Integer.parseInt(sdate) > Integer.parseInt(edate)) {
				resBoolean = false;
			}
		}
		
		return resBoolean;
	}
}