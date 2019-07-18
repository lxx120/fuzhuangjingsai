package com.match.reviewresult.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface ExcellentPersonService {

	/**
	 * 
	 * <p>功能描述：查询学生积分</p>
	 * <p>方法名：findExcellentPersonPage</p>
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月29日 下午2:42:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findExcellentPersonPage(PageModel pageModel)  throws Exception;
}
