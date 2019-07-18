package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.DivisionSchool;

public interface DivisionSchoolService {

	
	/**
	 * 
	 * <p>功能描述：赛区负责人-查询未绑定的学校</p>
	 * <p>方法名：findOthersCollegePage</p>
	 * <p>@param competitionid
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月1日 下午4:24:17</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findOthersCollegePage(String province,String city,long divisionid,String name,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：赛区负责人-查询绑定的学校</p>
	 * <p>方法名：findBangDingCollege</p>
	 * <p>@param province
	 * <p>@param city
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@param pageModel
	 * <p>@return</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月1日 下午5:40:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findBangDingCollege(String province,String city,long divisionid,String name,PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：删除赛区绑定的学校</p>
	 * <p>方法名：deleteDivisionSchool</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月1日 下午5:41:35</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteDivisionSchool(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：批量添加赛区学校</p>
	 * <p>方法名：addBatchDivisionSchool</p>
	 * <p>@param divisionid
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月1日 下午6:30:06</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addBatchDivisionSchool(long divisionid,List<Map<String,Object>> list)  throws Exception;
	
	public  int  addDivisionSchool(long competitionid,long divisionid,DivisionSchool deDivisionSchool)  throws Exception;

}
