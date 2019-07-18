package com.match.reviewresult.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface ExcellentCollegeService {

	/**
	 * 
	 * <p>功能描述：查询学校积分</p>
	 * <p>方法名：findExcellentCollege</p>
	 * <p>@param competitionid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月29日 下午3:08:00</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findExcellentCollege(PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-查询优秀组织院校</p>
	 * <p>方法名：findExcellentCollege</p>
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月29日 下午5:31:32</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findExcellentCollege1()  throws  Exception;
}
