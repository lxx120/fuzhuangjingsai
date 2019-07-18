package com.match.common;

import javax.servlet.http.HttpServletRequest;

import com.match.common.result.PageModel;

public class Pageparam {

	public  PageModel  getPageParam(HttpServletRequest r) 
	{
		int page = RequestParam.getInt(r, "page");
		int pagesize = RequestParam.getInt(r, "pagesize");
		page = (page-1)  * pagesize;
		
		PageModel pageparam = new PageModel();
		pageparam.setPage(page);
		pageparam.setPagesize(pagesize);
		
		return  pageparam;
	}
}
