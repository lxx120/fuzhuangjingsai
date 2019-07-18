package com.match.common.result;

import java.io.Serializable;
import java.util.List;

/**
 *@Title:PageResult.java
 *@date:2016年2月25日
 *@author:guolq无
 */

public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = 93799394562221545L;
	private int rowCount;
	private int pagesize;
	private int pages;
	private int page;
	private String errmsg;
	/**
	 *记录列表
	 */
	private List<T> items;
	
	private String hint;
	private int Code;
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public int getCode() {
		return Code;
	}
	public void setCode(int code) {
		Code = code;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
