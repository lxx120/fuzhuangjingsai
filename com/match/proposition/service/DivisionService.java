package com.match.proposition.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.Division;

public interface DivisionService {

	/**
	 * 
	 * <p>功能描述：添加赛区</p>
	 * <p>方法名：addDivision</p>
	 * <p>@param division
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月18日 下午6:24:58</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addDivision(Division division)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改赛区</p>
	 * <p>方法名：updateDivision</p>
	 * <p>@param division
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月18日 下午6:25:06</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateDivision(Division division)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询赛区（分页）</p>
	 * <p>方法名：findDivisionPage</p>
	 * <p>@param competitionid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月18日 下午6:25:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public   PageResult<Map<String,Object>>  findDivisionPage(String name,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询赛区（单个）</p>
	 * <p>方法名：findDivisionById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月18日 下午6:25:35</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findDivisionById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除赛区</p>
	 * <p>方法名：deleteDivision</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月18日 下午6:25:47</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteDivision(long id)  throws  Exception;
}
