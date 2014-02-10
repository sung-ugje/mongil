/**
 * 메일 발송 ServiceImpl
 */
package com.openapi.service.common;

import com.openapi.domain.common.MailSendHistory;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.DateUtils;
import com.openapi.framework.utils.EnvPropUtils;
import com.openapi.framework.utils.StringUtil;
import com.openapi.mapper.common.MailSendMapper;
import com.openapi.service.BaseServiceImpl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailSendServiceImpl extends BaseServiceImpl implements MailSendService {
	
	@Autowired private MailSendMapper mailSendMapper;

	/**
	 * 오픈API 내부오류 발생시 관리자에게 메일 발송
	 * @param e : Exception
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void sendMailByInternalException(Exception e) throws Exception {
		MailSendHistory mailSendHistory = new MailSendHistory();
		
		String message = StringUtil.trimToEmpty(StringUtil.getStackTraceToString(e));
		String title = "";
		
		if (!"".equals(message)) {
			title = StringUtil.trimToEmpty(StringUtil.substring(message, 0, message.indexOf(":")));
			title = "[오픈API] " + title + " 발생 (" + DateUtils.getFullDate() + ")";
			
		} else {
			title = "[오픈API] Exception 발생 (" + DateUtils.getFullDate() + ") - 로그분석 요망";
		}
		
		if (title.getBytes("UTF-8").length > 1000) {
			title = "[오픈API] Internal Exception 발생 (" + DateUtils.getFullDate() + ")";
		}
		
		//logger.debug("message : {}", message);
		//logger.debug("title : {}", title);
		
		/*오류메일을 받을 사람 목록*/
		String[] receiverMails = ArrayUtils.toArray(
			 "ekxkaks0@naver.com"/*성욱제*/
		);
		
		if (receiverMails == null || receiverMails.length == 0) {
			ArrayUtils.add(receiverMails, EnvPropUtils.getPropertyValue("openapi.admin.email.addr"));
		}
		
		for (int i=0; i<receiverMails.length; i++) {
			/*메일 주소가 존재하면 발송*/
			if (!"".equals(StringUtil.trimToEmpty(receiverMails[i]))) {
				mailSendHistory.setTrnsTpCd(MgConstants.MAIL_TRNS_KIND_CD_SYSTEM);
				mailSendHistory.setMailTpCd(MgConstants.MAIL_SEND_TYPE_CD_SYSTEM);
				mailSendHistory.setMbrNo(0);
				mailSendHistory.setAdreNm(EnvPropUtils.getPropertyValue("openapi.admin.email.name"));
				mailSendHistory.setAdreMail(StringUtil.trimToEmpty(receiverMails[i]));
				mailSendHistory.setSndStatCd(MgConstants.MAIL_SEND_STATUS_CD_WAIT);
				mailSendHistory.setSndTgtYn(MgConstants.FLAG_Y);
				mailSendHistory.setDppsNm(EnvPropUtils.getPropertyValue("openapi.admin.email.name"));
				mailSendHistory.setDppsMail(EnvPropUtils.getPropertyValue("openapi.system.email.addr"));
				mailSendHistory.setDppsNo(MgConstants.SYSTEM_USER_NO);
				
				mailSendHistory.setMailTit(title);
				mailSendHistory.setMailCont(this.getEmailContent(message));
				
				mailSendHistory.setLoginUsrNo(MgConstants.SYSTEM_USER_NO);
				mailSendHistory.setPgmId(programIdCache.getPgmId("MailSendMapper.insertMailSendHist"));
				
				mailSendMapper.insertMailSendHist(mailSendHistory);
			}
		}
	}
	
	/**
	 * 메일 본문 내용(html) 만들기
	 * @param contents : 메일 내용
	 * @return
	 */
	private String getEmailContent(String contents) {
		sb.setLength(0);
		
		sb.append("<html>" + MgConstants.CRLF);
		sb.append("<head>" + MgConstants.CRLF);
		sb.append("	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" + MgConstants.CRLF);
		sb.append("</head>" + MgConstants.CRLF);
		sb.append("	<body style='margin:0;'>" + MgConstants.CRLF);
		sb.append("<table align='center' border='0' cellpadding='0' cellspacing='0' style='width:734px;'>" + MgConstants.CRLF);
		sb.append("	<tr>" + MgConstants.CRLF);
		sb.append("		<td width='9'></td>" + MgConstants.CRLF);
		sb.append("		<td width='682' style='padding-left:30px; border-left:1px solid #ece5d9;; border-right:1px solid #ece5d9; vertical-align:top; text-align:left;'>" + MgConstants.CRLF);
		
		sb.append("<p>" + MgConstants.CRLF);
		sb.append(StringUtil.nl2br(contents) + MgConstants.CRLF);
		sb.append("</p>" + MgConstants.CRLF);
		
		sb.append("		</td>" + MgConstants.CRLF);
		sb.append("		<td width='11'></td>" + MgConstants.CRLF);
		sb.append("	</tr>" + MgConstants.CRLF);
		sb.append("</table>" + MgConstants.CRLF);
		sb.append("</body>" + MgConstants.CRLF);
		sb.append("</html>" + MgConstants.CRLF);
		
		//logger.debug(OAConstants.CRLF + "-------------------------------------------------------------" + OAConstants.CRLF + sb.toString());
		
		return sb.toString();
	}
}
