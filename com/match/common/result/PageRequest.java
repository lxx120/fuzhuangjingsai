package com.match.common.result;

import java.io.Serializable;

/** 
 * @author mengly 
 * @version 创建时间：2016年4月1日 下午5:29:00 
 * 类说明 ,start:startpage，  start=0 and pagesize=0表示取出所有的
 */
public class PageRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private boolean needPages;
	private int start;
	private Sort sort;
	public PageRequest(int start,int pageSize,  Sort sort) {
		this.pageSize = pageSize;
		this.start = start;
		this.sort = sort;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public boolean isNeedPages() {
		return needPages;
	}
	public void setNeedPages(boolean needPages) {
		this.needPages = needPages;
	}
	
}
