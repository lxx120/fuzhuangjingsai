package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.judges.model.CC;

public interface AssessGroupService {

	/**
	 * 
	 * <p>功能描述：根据分配id查询</p>
	 * <p>方法名：findAssessGroupList</p>
	 * <p>@param arrid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月29日 下午6:38:32</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findAssessGroupList(long arrid)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：通过分组id,去查询分组总个数，老师个数等</p>
	 * <p>方法名：findinfoByGroupId</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月22日 下午6:59:20</p>  
	 * <p>创建者：lxx</p>
	 */
	public  CC  findinfoByGroupId(long id)  throws  Exception;
}
