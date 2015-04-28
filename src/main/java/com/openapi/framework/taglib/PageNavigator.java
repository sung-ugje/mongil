package com.openapi.framework.taglib;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.openapi.domain.common.BaseDomain;
import com.openapi.framework.utils.EnvPropUtils;
import com.openapi.framework.utils.StringUtil;

public class PageNavigator extends RequestContextAwareTag {

	private static final long serialVersionUID = -8285183762033175905L;
	// === tag attributes ===
	/**
	 * 0000 현제 페이지를 담고있는 페리지 리스트
	 */
	private List<?> pagingList; // REQUIRED
	/**
	 * 페이징시 같이 넘길 파라메타를 담고있는 도메인
	 */
	private BaseDomain pagingParam; // REQUIRED
	/**
	 * 페이지 네비게시션이 호출하는 자바스크립트 함수명
	 */
	private String onClick; // OPTIONAL
	/**
	 * 0000
	 */
	private String quickPagination = "false"; // OPTIONAL

	
	protected int doStartTagInternal() throws Exception {
		if (this.pagingList != null && this.pagingList.size() > 0) {
			try {
				BaseDomain p = (BaseDomain) this.pagingList.get(0);
				if (this.pagingParam != null) {
					pageContext.getOut().write(this.getPageNavigatorHTML(this.pagingParam.getPg(), p.getTotalRecordCount(), this.pagingParam.getPs(), this.pagingParam.getPb()));
				}

			} catch(Exception e) {
				com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
			}
			
		} else {
			try {
				pageContext.getOut().write("");
				
			} catch (IOException e) {
				com.openapi.framework.utils.StringUtil.printExceptionTrace(e);
			}
		}
		
		return EVAL_PAGE;
	}

	protected String getPageNavigatorHTML(Integer pg, Integer totalRecordCount, Integer ps, Integer pb) {
		String onClickFunction = (this.onClick == null) ? "goPage" : this.onClick;
		int totalPages = (int) Math.ceil((double)totalRecordCount / (double)ps);
		int startPage = (int) Math.floor((double)(pg - 1) / (double)pb) * pb;
		int endPage = (startPage + pb < totalPages) ? (startPage + pb) : totalPages;
		int currentPage = 0;

		StringBuilder sb = new StringBuilder();
		
		if (pg > pb * 2)
			sb.append("<a href=\"javascript:;\" class=\"btn\" onclick=\"").append(onClickFunction).append("(1); return false;\"><img src=\"" + EnvPropUtils.getPropertyValue("openapi.img.prefix") + "/resource/imgs/kor/btn/pg_1.gif\" alt=\"처음\" /></a> ");
		
		if ((pg - pb) > 0)
			sb.append("<a href=\"javascript:;\" class=\"btn\" onclick=\"").append(onClickFunction).append("(").append(startPage).append("); return false;\"><img src=\"" + EnvPropUtils.getPropertyValue("openapi.img.prefix") + "/resource/imgs/kor/btn/pg_2.gif\" alt=\"이전\" /></a> ");

		sb.append("<span class=\"num\">");
		
		for (int idx = startPage; idx < endPage; idx++)	{
			currentPage = idx + 1;
			
			if (startPage == (currentPage - 1)) {
				if (pg == currentPage) {
					sb.append("<a href=\"javascript:;\" class=\"first on\"><strong>").append(currentPage).append("</strong></a> ");
				} else {
					sb.append("<a href=\"javascript:;\" class=\"first\" onclick=\"").append(onClickFunction).append("(").append(currentPage).append("); return false;\">").append(currentPage).append("</a> ");
				}
				
			} else {
				if (pg == currentPage) {
					sb.append("<a href=\"javascript:;\" class=\"on\"><strong>").append(currentPage).append("</strong></a> ");
				} else {
					sb.append("<a href=\"javascript:;\" onclick=\"").append(onClickFunction).append("(").append(currentPage).append("); return false;\">").append(currentPage).append("</a> ");
				}
			}
		}
		
		sb.append("</span>");

		if ((endPage + 1) <= totalPages)
			sb.append("<a href=\"javascript:;\" class=\"btn\" onclick=\"").append(onClickFunction).append("(").append((startPage + pb + 1)).append("); return false;\"><img src=\"" + EnvPropUtils.getPropertyValue("openapi.img.prefix") + "/resource/imgs/kor/btn/pg_3.gif\" alt=\"다음\" /></a> ");
		
		if (endPage < totalPages )
			sb.append("<a href=\"javascript:;\" class=\"btn\" onclick=\"").append(onClickFunction).append("(").append(totalPages).append("); return false;\"><img src=\"" + EnvPropUtils.getPropertyValue("openapi.img.prefix") + "/resource/imgs/kor/btn/pg_4.gif\" alt=\"끝\" /></a>");

		if (totalRecordCount > 0 && "true".equals(this.quickPagination)) {
			String randomId = StringUtil.replace(UUID.randomUUID().toString(), "-", "");
			
			sb.append("	<span class=\"quickPage\">");
			sb.append("	<input id=\"inputGoPage_").append(randomId).append("\" type=\"text\" name=\"inputGoPages\" class=\"inp1\" onfocus=\"this.select();\" value=\"").append(pg).append("\" itype=\"number\" />");
			sb.append("	<span name=\"totalPages\">/ ").append(totalPages).append("</span>");
			sb.append("	<span class=\"btnType1\"><a href=\"javascript:;\" onclick=\"").append(onClickFunction).append("($('#inputGoPage_").append(randomId).append("').val(), "+totalPages+")").append("; return false;\">바로가기</a></span>");
			sb.append("	</span>");
		}

		return sb.toString();
	}

	/**
	 * 필드 {@link #pagingList}의 값을 반환한다.
	 * @return {@link #pagingList}의 값
	 */
	public List<?> getPagingList() {
		return pagingList;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pagingList}의 값을 지정한다.
	 * @param pagingList 필드 {@link #pagingList}의 값
	 */
	public void setPagingList(List<?> pagingList) {
		this.pagingList = pagingList;
	}

	/**
	 * 필드 {@link #pagingParam}의 값을 반환한다.
	 * @return {@link #pagingParam}의 값
	 */
	public BaseDomain getPagingParam() {
		return pagingParam;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #pagingParam}의 값을 지정한다.
	 * @param pagingParam 필드 {@link #pagingParam}의 값
	 */
	public void setPagingParam(BaseDomain pagingParam) {
		this.pagingParam = pagingParam;
	}

	/**
	 * 필드 {@link #onClick}의 값을 반환한다.
	 * @return {@link #onClick}의 값
	 */
	public String getOnClick() {
		return onClick;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #onClick}의 값을 지정한다.
	 * @param onClick 필드 {@link #onClick}의 값
	 */
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	/**
	 * 필드 {@link #quickPagination}의 값을 반환한다.
	 * @return {@link #quickPagination}의 값
	 */
	public String getQuickPagination() {
		return quickPagination;
	}

	/**
	 * 지정한 파라메타로 필드 {@link #quickPagination}의 값을 지정한다.
	 * @param quickPagination 필드 {@link #quickPagination}의 값
	 */
	public void setQuickPagination(String quickPagination) {
		this.quickPagination = quickPagination;
	}
}
