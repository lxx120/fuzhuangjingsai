package com.match.judges.service;

import com.match.judges.model.AssessRole;

public interface AssessRoleService {

	/**
	 * 
	 * <p>功能描述：添加评审分配</p>
	 * <p>方法名：addAssessRole</p>
	 * <p>@param assessRole
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月29日 下午5:32:09</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addAssessRole(AssessRole assessRole)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改评审分配</p>
	 * <p>方法名：updateAssessRole</p>
	 * <p>@param assessRole
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月29日 下午5:32:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateAssessRole(AssessRole assessRole)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个评审分配</p>
	 * <p>方法名：findAssessRole</p>
	 * <p>@param assessRole
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月29日 下午5:32:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  findAssessRole(AssessRole assessRole)  throws Exception;
}
