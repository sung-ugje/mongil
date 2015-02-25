/**
 * 파일업로드 VO
 */
package com.openapi.domain.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("uploadedItem")
@JsonSerialize(include=Inclusion.NON_DEFAULT)
public class UploadedItem extends BaseDomain {

	private static final long serialVersionUID = 6576807652313495434L;

	/** 업로드된 풀 경로 (ex : /upload/product/ss45sd6d6d5f4df457d8fd.jpg) */
	private String uploadFilePath;
	
	/** 업로드된 디렉토리 경로 (ex : /upload/product/) */
	private String uploadPath;
	
	/** 업로드 파일 이름 (ex : 상품이미지.jpg) */
	private String orgFileName;
	
	/** 실제 저장되는 파일명 */
	private String saveFileName;
	
	/** 업로드 된 파일 크기 */
	private Long fileSize  = 0L;

	/**
	 * Constructor
	 */
	public UploadedItem() {}

	/**
	 * 필드 {@link #uploadFilePath}의 값을 반환한다.
	 * @return {@link #uploadFilePath}의 값
	 */
	public String getUploadFilePath() {
		return uploadFilePath;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #uploadFilePath}의 값을 지정한다.
	 * @param uploadFilePath 필드 {@link #uploadFilePath}의 값
	 */
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	/**
	 * 필드 {@link #uploadPath}의 값을 반환한다.
	 * @return {@link #uploadPath}의 값
	 */
	public String getUploadPath() {
		return uploadPath;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #uploadPath}의 값을 지정한다.
	 * @param uploadPath 필드 {@link #uploadPath}의 값
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	/**
	 * 필드 {@link #orgFileName}의 값을 반환한다.
	 * @return {@link #orgFileName}의 값
	 */
	public String getOrgFileName() {
		return orgFileName;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #orgFileName}의 값을 지정한다.
	 * @param orgFileName 필드 {@link #orgFileName}의 값
	 */
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	/**
	 * 필드 {@link #saveFileName}의 값을 반환한다.
	 * @return {@link #saveFileName}의 값
	 */
	public String getSaveFileName() {
		return saveFileName;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #saveFileName}의 값을 지정한다.
	 * @param saveFileName 필드 {@link #saveFileName}의 값
	 */
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	/**
	 * 필드 {@link #fileSize}의 값을 반환한다.
	 * @return {@link #fileSize}의 값
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #fileSize}의 값을 지정한다.
	 * @param fileSize 필드 {@link #fileSize}의 값
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	
}
