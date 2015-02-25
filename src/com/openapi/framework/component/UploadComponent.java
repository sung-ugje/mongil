/**
 * 파일업로드 Component
 */
package com.openapi.framework.component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.openapi.domain.common.UploadedItem;
import com.openapi.framework.constants.MgConstants;
import com.openapi.framework.utils.DateUtils;
import com.openapi.framework.utils.StringUtil;
import com.openapi.framework.utils.WebPathUtils;


@Component
public class UploadComponent extends BaseComponent {

	/**
	 * 파일 업로드 처리
	 * @param uploadSourceFile 업로드 소스
	 * @param uploadWebPath 업로드 할 웹 경로 (ex : /upload/product/2012/07/)
	 * @param isUniqueFileName 유니크한 파일명으로 저장할지 여부 (true:유니크한 파일명, false:원래 파일명)
	 * @return
	 * @throws IOException
	 */
	public UploadedItem upload(MultipartFile uploadSourceFile, String uploadPath, boolean isUniqueFileName) throws Exception {
		UploadedItem uploadedItem = new UploadedItem();
		
		if (uploadSourceFile != null && !uploadSourceFile.isEmpty()) {
			uploadPath = this.convertPath(uploadPath);

			this.makeUplaodPath(uploadPath);

			String orgFileName = uploadSourceFile.getOriginalFilename();
			String orgFileExt = FilenameUtils.getExtension(orgFileName).toLowerCase();

			String saveFileName = orgFileName;
			
			if (isUniqueFileName) {
				saveFileName = UUID.randomUUID().toString().replace("-", "") + "." + orgFileExt;
			}
			
			File uploadFile = new File(System.getProperty("webApp.root") + uploadPath + saveFileName);

			uploadSourceFile.transferTo(uploadFile);
			uploadedItem.setUploadFilePath(uploadPath + saveFileName);
			uploadedItem.setUploadPath(uploadPath);
			uploadedItem.setOrgFileName(orgFileName);
			uploadedItem.setSaveFileName(saveFileName);
			uploadedItem.setFileSize(uploadSourceFile.getSize());
			
			sb.setLength(0);
			sb.append("------------------- UploadComponent upload() Start --------------------" + MgConstants.CRLF);
			sb.append("uploadFilePath : " + uploadedItem.getUploadFilePath() + MgConstants.CRLF);
			sb.append("uploadPath : " + uploadedItem.getUploadPath() + MgConstants.CRLF);
			sb.append("orgFileName : " + uploadedItem.getOrgFileName() + MgConstants.CRLF);
			sb.append("savedFileName : " + uploadedItem.getSaveFileName() + MgConstants.CRLF);
			sb.append("fileSize : " + uploadedItem.getFileSize() + MgConstants.CRLF);
			sb.append("------------------- UploadComponent upload() End --------------------" + MgConstants.CRLF);
			
			logger.debug(MgConstants.CRLF + sb.toString());
		}
		
		return uploadedItem;
	}

	/**
	 *  파일 업로드 처리 (저장 파일명 강제 지정)
	 * @param uploadSourceFile 업로드 소스
	 * @param uploadPath 업로드 할 웹 경로 (ex : /upload/product/2012/07/)
	 * @param forcedSavedFileName 저장할 파일명 (확장자 제외)
	 * @return
	 * @throws IOException
	 */
	public UploadedItem upload(MultipartFile uploadSourceFile, String uploadPath, String forcedSavedFileName) throws Exception {
		UploadedItem uploadedItem = new UploadedItem();
		
		if (uploadSourceFile != null && !uploadSourceFile.isEmpty()) {
			uploadPath = this.convertPath(uploadPath);

			this.makeUplaodPath(uploadPath);

			String orgFileName = uploadSourceFile.getOriginalFilename();
			String orgFileExt = FilenameUtils.getExtension(orgFileName).toLowerCase();

			String saveFileName = forcedSavedFileName + "." + orgFileExt;
			File uploadFile = new File(FilenameUtils.concat(System.getProperty("webApp.root") + uploadPath, saveFileName));

			uploadSourceFile.transferTo(uploadFile);
			uploadedItem.setUploadFilePath(uploadPath + saveFileName);
			uploadedItem.setUploadPath(uploadPath);
			uploadedItem.setOrgFileName(orgFileName);
			uploadedItem.setSaveFileName(saveFileName);
			uploadedItem.setFileSize(uploadSourceFile.getSize());

			sb.setLength(0);
			sb.append("------------------- UploadComponent upload() Start --------------------" + MgConstants.CRLF);
			sb.append("uploadFilePath : " + uploadedItem.getUploadFilePath() + MgConstants.CRLF);
			sb.append("uploadPath : " + uploadedItem.getUploadPath() + MgConstants.CRLF);
			sb.append("orgFileName : " + uploadedItem.getOrgFileName() + MgConstants.CRLF);
			sb.append("savedFileName : " + uploadedItem.getSaveFileName() + MgConstants.CRLF);
			sb.append("fileSize : " + uploadedItem.getFileSize() + MgConstants.CRLF);
			sb.append("------------------- UploadComponent upload() End --------------------" + MgConstants.CRLF);
			
			logger.debug(MgConstants.CRLF + sb.toString());
		}
		
		return uploadedItem;
	}

	/**
	 * 파일업로드시 경로설정에 있는 동적파라미터 Convert
	 * @param uploadPath
	 * @return
	 */
	public String convertPath(String uploadPath) {
		String resultPath = StringUtil.replace(uploadPath, "/yyyy/", "/" + DateUtils.getYear() + "/");
		resultPath = StringUtil.replace(resultPath, "/MM/", "/" + DateUtils.getMonth() + "/");
		resultPath = StringUtil.replace(resultPath, "/dd/", "/" + DateUtils.getDay() + "/");
		resultPath = StringUtil.replace(resultPath, "/HH/", "/" + DateUtils.getHour() + "/");
		resultPath = StringUtil.replace(resultPath, "/mm/", "/" + DateUtils.getMinute() + "/");
		resultPath = StringUtil.replace(resultPath, "/W/", "/" + DateUtils.getWeekOfMonth() + "/");
		resultPath = StringUtil.replace(resultPath, "/w/", "/" + DateUtils.getWeekOfYear() + "/");
		
		return resultPath;
	}
	
	/**
	 * 허락된 파일확장자만 업로드한다.
	 * @param fileName
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean checkAllowFile(String fileName, String type) throws Exception {
		boolean bRes = false;
		
		String fileExt = StringUtil.upperCase(FilenameUtils.getExtension(fileName));
		
		String[] restricFileExt = ArrayUtils.toArray("ASP", "ASPX", "ASA", "COM", "EXE", "BAT", "JS", "HTC", "PHP", "PHP3", "PHP4", "PHP5", "PHTML", "JSP", "HTML", "HTM", "SH", "SER");
		String[] allowImageFileExt = ArrayUtils.toArray("JPG", "GIF", "PNG", "BMP", "JPEG");
		String[] allowDocFileExt = ArrayUtils.toArray("HWP", "DOC", "PPT", "XLS", "PPTX", "XLSX");
		
		if ("IMAGE".equals(type)) {
			if (ArrayUtils.contains(allowImageFileExt, fileExt)) {
				bRes = true;
			}
			
		} else if ("DOC".equals(type)) {
			if (ArrayUtils.contains(allowDocFileExt, fileExt)) {
				bRes = true;
			}
			
		} else {
			if (!ArrayUtils.contains(restricFileExt, fileExt)) {
				bRes = true;
			}
		}
		
		return bRes;
	}
	
	/**
	 * 디렉토리 생성
	 * @param uploadPath
	 * @throws Exception
	 */
	public void makeUplaodPath(String uploadPath) throws Exception {
		this.makeUplaodPath(uploadPath, true);
	}
	
	/**
	 * 디렉토리 생성
	 * @param uploadPath
	 * @throws Exception
	 */
	public void makeUplaodPath(String uploadPath, Boolean isWebDirectory) throws Exception {
		File uploadDirectory = null;
		
		if (isWebDirectory) {
			uploadDirectory = new File(WebPathUtils.getAbsPathFromWebPath(uploadPath));
		} else {
			uploadDirectory = new File(uploadPath);
		}
		
		logger.debug("uploadDirectory : {}", uploadDirectory.getPath());
		
		if (!uploadDirectory.exists()) {
			FileUtils.forceMkdir(uploadDirectory);
		}
	}
	
	/**
	 * 업로드 경로/파일 삭제
	 * @param path
	 */
	public void deleteUploadPathFile(String path) {
		this.deleteUploadPathFile(path, true);
	}
	
	/**
	 * 업로드 경로/파일 삭제
	 * @param path
	 */
	public void deleteUploadPathFile(String path, Boolean isWebDirectory) {
		if (StringUtil.isNotEmpty(path) && !"/".equals(path)) {
			logger.debug("deleteUploadPathFile path : {}", StringUtil.trimToEmpty(path));
			
			File deleteFile = null;
			
			if (isWebDirectory) {
				deleteFile = new File(FilenameUtils.normalize(System.getProperty("webApp.root") + path));
			} else {
				deleteFile = new File(FilenameUtils.normalize(path));
			}
			
			try {
				FileUtils.deleteDirectory(deleteFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 업로드 파일 전체 삭제
	 * @param path
	 */
	public void deleteUploadAllFile(String path) {
		this.deleteUploadAllFile(path, true);
	}
	
	/**
	 * 업로드 파일 전체 삭제
	 * @param path
	 */
	public void deleteUploadAllFile(String path, Boolean isWebDirectory) {
		if (StringUtil.isNotEmpty(path) && !"/".equals(path)) {
			logger.debug("deleteUploadAllFile path : {}", StringUtil.trimToEmpty(path));
			
			File fileDir = new File(path);
			
			if (isWebDirectory) {
				fileDir = new File(FilenameUtils.normalize(System.getProperty("webApp.root") + path));
			} else {
				fileDir = new File(FilenameUtils.normalize(path));
			}
			
			/*디렉토리일 경우*/
			if (fileDir != null && fileDir.isDirectory()) {
				File[] files = fileDir.listFiles();
				
				if (files != null && files.length > 0) {
					for (int i=0; i<files.length; i++) {
						File deleteFile = files[i];
						
						try {
							FileUtils.deleteDirectory(deleteFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
