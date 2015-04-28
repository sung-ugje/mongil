/**
 * 메일 발송 이력 VO
 */
package com.openapi.domain.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mailSendHistory")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class MailSendHistory extends BaseDomain {

	private static final long serialVersionUID = -8179429986445400862L;
	
	/*MM_MAIL_SND_HIST*/
	/**메일발송 번호*/
	private Integer mailSndNo;
	
	/**전송유형 코드*/
	private String trnsTpCd;
	
	/**메일 번호*/
	private Integer mailNo;
	
	/**메일유형 코드*/
	private String mailTpCd;
	
	/**회원 번호*/
	private Integer mbrNo;
	
	/**수신자 명*/
	private String adreNm;
	
	/**수신자 메일주소*/
	private String adreMail;
	
	/**내용 URL*/
	private String contUrl;
	
	/**발송상태 코드*/
	private String sndStatCd;
	
	/**발송 일시*/
	private String sndDtime;
	
	/**발송대상 여부*/
	private String sndTgtYn;
	
	/**메일 제목*/
	private String mailTit;
	
	/**주문 번호*/
	private Integer ordNo;
	
	/**상담 번호*/
	private Integer cnslNo;
	
	/**수신결과 코드*/
	private String rcvRsltCd;
	
	/**메일 내용*/
	private String mailCont;
	
	/**발송자 메일주소*/
	private String dppsMail;
	
	/**발송자 번호*/
	private Integer dppsNo;
	
	/**적용 태그1*/
	private String aplyTag1;
	
	/**적용 태그2*/
	private String aplyTag2;
	
	/**적용 태그3*/
	private String aplyTag3;
	
	/**적용 태그4*/
	private String aplyTag4;
	
	/**적용 태그5*/
	private String aplyTag5;
	
	/**적용 태그6*/
	private String aplyTag6;
	
	/**적용 태그7*/
	private String aplyTag7;
	
	/**적용 태그8*/
	private String aplyTag8;
	
	/**적용 태그9*/
	private String aplyTag9;
	
	/**적용 태그10*/
	private String aplyTag10;
	
	/**메일 오픈 일시*/
	private String mailOpenDtime;
	
	/**메일 클릭 일시*/
	private String mailClckDtime;
	
	/**발송 예약 일시*/
	private String sndRsvDtime;
	
	/**발송자 명*/
	private String dppsNm;
	
	/**대상자 선정 조건키*/
	private String trgtChocTp;
	
	/**대상자 선정 조건*/
	private String trgtChocCond;

	/**
	 * Constructor
	 */
	public MailSendHistory() {}

	/**
	 * 필드 {@link #mailSndNo}의 값을 반환한다.
	 * @return {@link #mailSndNo}의 값
	 */
	public Integer getMailSndNo() {
		return mailSndNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailSndNo}의 값을 지정한다.
	 * @param mailSndNo 필드 {@link #mailSndNo}의 값
	 */
	public void setMailSndNo(Integer mailSndNo) {
		this.mailSndNo = mailSndNo;
	}

	/**
	 * 필드 {@link #trnsTpCd}의 값을 반환한다.
	 * @return {@link #trnsTpCd}의 값
	 */
	public String getTrnsTpCd() {
		return trnsTpCd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #trnsTpCd}의 값을 지정한다.
	 * @param trnsTpCd 필드 {@link #trnsTpCd}의 값
	 */
	public void setTrnsTpCd(String trnsTpCd) {
		this.trnsTpCd = trnsTpCd;
	}

	/**
	 * 필드 {@link #mailNo}의 값을 반환한다.
	 * @return {@link #mailNo}의 값
	 */
	public Integer getMailNo() {
		return mailNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailNo}의 값을 지정한다.
	 * @param mailNo 필드 {@link #mailNo}의 값
	 */
	public void setMailNo(Integer mailNo) {
		this.mailNo = mailNo;
	}

	/**
	 * 필드 {@link #mailTpCd}의 값을 반환한다.
	 * @return {@link #mailTpCd}의 값
	 */
	public String getMailTpCd() {
		return mailTpCd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailTpCd}의 값을 지정한다.
	 * @param mailTpCd 필드 {@link #mailTpCd}의 값
	 */
	public void setMailTpCd(String mailTpCd) {
		this.mailTpCd = mailTpCd;
	}

	/**
	 * 필드 {@link #mbrNo}의 값을 반환한다.
	 * @return {@link #mbrNo}의 값
	 */
	public Integer getMbrNo() {
		return mbrNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mbrNo}의 값을 지정한다.
	 * @param mbrNo 필드 {@link #mbrNo}의 값
	 */
	public void setMbrNo(Integer mbrNo) {
		this.mbrNo = mbrNo;
	}

	/**
	 * 필드 {@link #adreNm}의 값을 반환한다.
	 * @return {@link #adreNm}의 값
	 */
	public String getAdreNm() {
		return adreNm;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #adreNm}의 값을 지정한다.
	 * @param adreNm 필드 {@link #adreNm}의 값
	 */
	public void setAdreNm(String adreNm) {
		this.adreNm = adreNm;
	}

	/**
	 * 필드 {@link #adreMail}의 값을 반환한다.
	 * @return {@link #adreMail}의 값
	 */
	public String getAdreMail() {
		return adreMail;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #adreMail}의 값을 지정한다.
	 * @param adreMail 필드 {@link #adreMail}의 값
	 */
	public void setAdreMail(String adreMail) {
		this.adreMail = adreMail;
	}

	/**
	 * 필드 {@link #contUrl}의 값을 반환한다.
	 * @return {@link #contUrl}의 값
	 */
	public String getContUrl() {
		return contUrl;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #contUrl}의 값을 지정한다.
	 * @param contUrl 필드 {@link #contUrl}의 값
	 */
	public void setContUrl(String contUrl) {
		this.contUrl = contUrl;
	}

	/**
	 * 필드 {@link #sndStatCd}의 값을 반환한다.
	 * @return {@link #sndStatCd}의 값
	 */
	public String getSndStatCd() {
		return sndStatCd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #sndStatCd}의 값을 지정한다.
	 * @param sndStatCd 필드 {@link #sndStatCd}의 값
	 */
	public void setSndStatCd(String sndStatCd) {
		this.sndStatCd = sndStatCd;
	}

	/**
	 * 필드 {@link #sndDtime}의 값을 반환한다.
	 * @return {@link #sndDtime}의 값
	 */
	public String getSndDtime() {
		return sndDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #sndDtime}의 값을 지정한다.
	 * @param sndDtime 필드 {@link #sndDtime}의 값
	 */
	public void setSndDtime(String sndDtime) {
		this.sndDtime = sndDtime;
	}

	/**
	 * 필드 {@link #sndTgtYn}의 값을 반환한다.
	 * @return {@link #sndTgtYn}의 값
	 */
	public String getSndTgtYn() {
		return sndTgtYn;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #sndTgtYn}의 값을 지정한다.
	 * @param sndTgtYn 필드 {@link #sndTgtYn}의 값
	 */
	public void setSndTgtYn(String sndTgtYn) {
		this.sndTgtYn = sndTgtYn;
	}

	/**
	 * 필드 {@link #mailTit}의 값을 반환한다.
	 * @return {@link #mailTit}의 값
	 */
	public String getMailTit() {
		return mailTit;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailTit}의 값을 지정한다.
	 * @param mailTit 필드 {@link #mailTit}의 값
	 */
	public void setMailTit(String mailTit) {
		this.mailTit = mailTit;
	}

	/**
	 * 필드 {@link #ordNo}의 값을 반환한다.
	 * @return {@link #ordNo}의 값
	 */
	public Integer getOrdNo() {
		return ordNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #ordNo}의 값을 지정한다.
	 * @param ordNo 필드 {@link #ordNo}의 값
	 */
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}

	/**
	 * 필드 {@link #cnslNo}의 값을 반환한다.
	 * @return {@link #cnslNo}의 값
	 */
	public Integer getCnslNo() {
		return cnslNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #cnslNo}의 값을 지정한다.
	 * @param cnslNo 필드 {@link #cnslNo}의 값
	 */
	public void setCnslNo(Integer cnslNo) {
		this.cnslNo = cnslNo;
	}

	/**
	 * 필드 {@link #rcvRsltCd}의 값을 반환한다.
	 * @return {@link #rcvRsltCd}의 값
	 */
	public String getRcvRsltCd() {
		return rcvRsltCd;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #rcvRsltCd}의 값을 지정한다.
	 * @param rcvRsltCd 필드 {@link #rcvRsltCd}의 값
	 */
	public void setRcvRsltCd(String rcvRsltCd) {
		this.rcvRsltCd = rcvRsltCd;
	}

	/**
	 * 필드 {@link #mailCont}의 값을 반환한다.
	 * @return {@link #mailCont}의 값
	 */
	public String getMailCont() {
		return mailCont;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailCont}의 값을 지정한다.
	 * @param mailCont 필드 {@link #mailCont}의 값
	 */
	public void setMailCont(String mailCont) {
		this.mailCont = mailCont;
	}

	/**
	 * 필드 {@link #dppsMail}의 값을 반환한다.
	 * @return {@link #dppsMail}의 값
	 */
	public String getDppsMail() {
		return dppsMail;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #dppsMail}의 값을 지정한다.
	 * @param dppsMail 필드 {@link #dppsMail}의 값
	 */
	public void setDppsMail(String dppsMail) {
		this.dppsMail = dppsMail;
	}

	/**
	 * 필드 {@link #dppsNo}의 값을 반환한다.
	 * @return {@link #dppsNo}의 값
	 */
	public Integer getDppsNo() {
		return dppsNo;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #dppsNo}의 값을 지정한다.
	 * @param dppsNo 필드 {@link #dppsNo}의 값
	 */
	public void setDppsNo(Integer dppsNo) {
		this.dppsNo = dppsNo;
	}

	/**
	 * 필드 {@link #aplyTag1}의 값을 반환한다.
	 * @return {@link #aplyTag1}의 값
	 */
	public String getAplyTag1() {
		return aplyTag1;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag1}의 값을 지정한다.
	 * @param aplyTag1 필드 {@link #aplyTag1}의 값
	 */
	public void setAplyTag1(String aplyTag1) {
		this.aplyTag1 = aplyTag1;
	}

	/**
	 * 필드 {@link #aplyTag2}의 값을 반환한다.
	 * @return {@link #aplyTag2}의 값
	 */
	public String getAplyTag2() {
		return aplyTag2;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag2}의 값을 지정한다.
	 * @param aplyTag2 필드 {@link #aplyTag2}의 값
	 */
	public void setAplyTag2(String aplyTag2) {
		this.aplyTag2 = aplyTag2;
	}

	/**
	 * 필드 {@link #aplyTag3}의 값을 반환한다.
	 * @return {@link #aplyTag3}의 값
	 */
	public String getAplyTag3() {
		return aplyTag3;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag3}의 값을 지정한다.
	 * @param aplyTag3 필드 {@link #aplyTag3}의 값
	 */
	public void setAplyTag3(String aplyTag3) {
		this.aplyTag3 = aplyTag3;
	}

	/**
	 * 필드 {@link #aplyTag4}의 값을 반환한다.
	 * @return {@link #aplyTag4}의 값
	 */
	public String getAplyTag4() {
		return aplyTag4;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag4}의 값을 지정한다.
	 * @param aplyTag4 필드 {@link #aplyTag4}의 값
	 */
	public void setAplyTag4(String aplyTag4) {
		this.aplyTag4 = aplyTag4;
	}

	/**
	 * 필드 {@link #aplyTag5}의 값을 반환한다.
	 * @return {@link #aplyTag5}의 값
	 */
	public String getAplyTag5() {
		return aplyTag5;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag5}의 값을 지정한다.
	 * @param aplyTag5 필드 {@link #aplyTag5}의 값
	 */
	public void setAplyTag5(String aplyTag5) {
		this.aplyTag5 = aplyTag5;
	}

	/**
	 * 필드 {@link #aplyTag6}의 값을 반환한다.
	 * @return {@link #aplyTag6}의 값
	 */
	public String getAplyTag6() {
		return aplyTag6;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag6}의 값을 지정한다.
	 * @param aplyTag6 필드 {@link #aplyTag6}의 값
	 */
	public void setAplyTag6(String aplyTag6) {
		this.aplyTag6 = aplyTag6;
	}

	/**
	 * 필드 {@link #aplyTag7}의 값을 반환한다.
	 * @return {@link #aplyTag7}의 값
	 */
	public String getAplyTag7() {
		return aplyTag7;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag7}의 값을 지정한다.
	 * @param aplyTag7 필드 {@link #aplyTag7}의 값
	 */
	public void setAplyTag7(String aplyTag7) {
		this.aplyTag7 = aplyTag7;
	}

	/**
	 * 필드 {@link #aplyTag8}의 값을 반환한다.
	 * @return {@link #aplyTag8}의 값
	 */
	public String getAplyTag8() {
		return aplyTag8;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag8}의 값을 지정한다.
	 * @param aplyTag8 필드 {@link #aplyTag8}의 값
	 */
	public void setAplyTag8(String aplyTag8) {
		this.aplyTag8 = aplyTag8;
	}

	/**
	 * 필드 {@link #aplyTag9}의 값을 반환한다.
	 * @return {@link #aplyTag9}의 값
	 */
	public String getAplyTag9() {
		return aplyTag9;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag9}의 값을 지정한다.
	 * @param aplyTag9 필드 {@link #aplyTag9}의 값
	 */
	public void setAplyTag9(String aplyTag9) {
		this.aplyTag9 = aplyTag9;
	}

	/**
	 * 필드 {@link #aplyTag10}의 값을 반환한다.
	 * @return {@link #aplyTag10}의 값
	 */
	public String getAplyTag10() {
		return aplyTag10;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #aplyTag10}의 값을 지정한다.
	 * @param aplyTag10 필드 {@link #aplyTag10}의 값
	 */
	public void setAplyTag10(String aplyTag10) {
		this.aplyTag10 = aplyTag10;
	}

	/**
	 * 필드 {@link #mailOpenDtime}의 값을 반환한다.
	 * @return {@link #mailOpenDtime}의 값
	 */
	public String getMailOpenDtime() {
		return mailOpenDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailOpenDtime}의 값을 지정한다.
	 * @param mailOpenDtime 필드 {@link #mailOpenDtime}의 값
	 */
	public void setMailOpenDtime(String mailOpenDtime) {
		this.mailOpenDtime = mailOpenDtime;
	}

	/**
	 * 필드 {@link #mailClckDtime}의 값을 반환한다.
	 * @return {@link #mailClckDtime}의 값
	 */
	public String getMailClckDtime() {
		return mailClckDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #mailClckDtime}의 값을 지정한다.
	 * @param mailClckDtime 필드 {@link #mailClckDtime}의 값
	 */
	public void setMailClckDtime(String mailClckDtime) {
		this.mailClckDtime = mailClckDtime;
	}

	/**
	 * 필드 {@link #sndRsvDtime}의 값을 반환한다.
	 * @return {@link #sndRsvDtime}의 값
	 */
	public String getSndRsvDtime() {
		return sndRsvDtime;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #sndRsvDtime}의 값을 지정한다.
	 * @param sndRsvDtime 필드 {@link #sndRsvDtime}의 값
	 */
	public void setSndRsvDtime(String sndRsvDtime) {
		this.sndRsvDtime = sndRsvDtime;
	}

	/**
	 * 필드 {@link #dppsNm}의 값을 반환한다.
	 * @return {@link #dppsNm}의 값
	 */
	public String getDppsNm() {
		return dppsNm;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #dppsNm}의 값을 지정한다.
	 * @param dppsNm 필드 {@link #dppsNm}의 값
	 */
	public void setDppsNm(String dppsNm) {
		this.dppsNm = dppsNm;
	}

	/**
	 * 필드 {@link #trgtChocTp}의 값을 반환한다.
	 * @return {@link #trgtChocTp}의 값
	 */
	public String getTrgtChocTp() {
		return trgtChocTp;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #trgtChocTp}의 값을 지정한다.
	 * @param trgtChocTp 필드 {@link #trgtChocTp}의 값
	 */
	public void setTrgtChocTp(String trgtChocTp) {
		this.trgtChocTp = trgtChocTp;
	}

	/**
	 * 필드 {@link #trgtChocCond}의 값을 반환한다.
	 * @return {@link #trgtChocCond}의 값
	 */
	public String getTrgtChocCond() {
		return trgtChocCond;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #trgtChocCond}의 값을 지정한다.
	 * @param trgtChocCond 필드 {@link #trgtChocCond}의 값
	 */
	public void setTrgtChocCond(String trgtChocCond) {
		this.trgtChocCond = trgtChocCond;
	}

}
