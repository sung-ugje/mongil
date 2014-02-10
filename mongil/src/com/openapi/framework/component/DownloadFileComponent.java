/**
 * 파일다운로드 콤포넌트
 */
package com.openapi.framework.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.openapi.controller.BaseController;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.MessageUtils;
import com.openapi.framework.utils.WebPathUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;


@Component
public class DownloadFileComponent extends BaseComponent {

	/**
	 * 첨부파일 다운로드
	 * @param webFilePath
	 * @param downFileName
	 * @param req
	 * @param res
	 * @throws UnsupportedEncodingException
	 * @throws ModelAndViewDefiningException
	 */
	public void download(String webFilePath, String downFileName, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("WebPathUtils.getAbsPathFromWebPath(webFilePath) : {}", WebPathUtils.getAbsPathFromWebPath(webFilePath));

		File downloadFile = new File(WebPathUtils.getAbsPathFromWebPath(StringUtils.trimToEmpty(webFilePath)));
		this.download(downloadFile, StringUtils.trimToEmpty(downFileName), req, res);
	}
	
	/**
	 * 첨부파일 다운로드
	 * @param webFilePath
	 * @param req
	 * @param res
	 * @throws UnsupportedEncodingException
	 * @throws ModelAndViewDefiningException
	 */
	public void download(String webFilePath, HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("WebPathUtils.getAbsPathFromWebPath(webFilePath) : {}", WebPathUtils.getAbsPathFromWebPath(webFilePath));

		File downloadFile = new File(WebPathUtils.getAbsPathFromWebPath(StringUtils.trimToEmpty(webFilePath)));
		this.download(downloadFile, null, req, res);
	}

	/**
	 * 첨부파일 다운로드
	 * @param downloadFile
	 * @param downFileName
	 * @param req
	 * @param res
	 * @throws UnsupportedEncodingException
	 * @throws ModelAndViewDefiningException
	 */
	public void download(File downloadFile, String downFileName, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (downloadFile != null && downloadFile.exists() && downloadFile.isFile()) {
			if ("".equals(StringUtils.trimToEmpty(downFileName))) {
				downFileName = StringUtils.trimToEmpty(downloadFile.getName());
			}
			
			//String mimeType = req.getSession().getServletContext().getMimeType(downloadFile.getAbsolutePath());
			String mimeType = "";

			if ("".equals(StringUtils.trimToEmpty(mimeType))) {
				mimeType = "application/octet-stream";
			}

			String userAgent = req.getHeader("User-Agent");

			/*
			 * 브라우저에 따른 설정
			 */
			if (userAgent.indexOf("MSIE") >= 0) {
				downFileName = URLEncoder.encode(downFileName, "UTF-8");
			} else {
				downFileName = new String(downFileName.getBytes("UTF-8"), "latin1");
			}
			
			sb.setLength(0);
			sb.append("-------------------------------------------------" + MgConstants.CRLF);
			sb.append("mimeType : " + mimeType + MgConstants.CRLF);
			sb.append("userAgent : " + userAgent + MgConstants.CRLF);
			sb.append("downFileName : " + downFileName + MgConstants.CRLF);
			
			logger.debug(MgConstants.CRLF + sb.toString());

			res.setContentType(mimeType);

			if ((int) downloadFile.length() > 0) {
				res.setContentLength((int) downloadFile.length());
			}

			res.setHeader("Content-Disposition", "attachment; fileName=\"" + StringUtils.replace(downFileName, "+", "%20") + "\"");
			res.setHeader("Content-Transfer-Encoding", "binary");
			
			FileInputStream fis = new FileInputStream(downloadFile);

			try {
				FileCopyUtils.copy(fis, res.getOutputStream());

			} catch(IOException ioe) {
				com.openapi.framework.utils.StringUtil.printExceptionTrace(ioe);
			} catch(Exception e) {
				com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
			} finally {
				IOUtils.closeQuietly(fis);
			}

		} else {
			logger.error("File download Error : " + MessageUtils.getMessage(""));
			
			ModelAndView mav = new ModelAndView(BaseController.AFTER_ACTION_VIEW);
			mav.addObject("alertMessage", MessageUtils.getMessage(""));
			
			throw new ModelAndViewDefiningException(mav);
		}
	}
}
