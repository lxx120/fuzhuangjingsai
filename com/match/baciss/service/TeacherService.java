package com.match.baciss.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface TeacherService {

	/**
	 * 
	 * <p>功能描述：查询所有学校</p>
	 * <p>方法名：findAllTeacher</p>
	 * <p>@param name
	 * <p>@param pgone
	 * <p>@param collegeid
	 * <p>@param departmentid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月28日 上午10:28:56</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findAllTeacher(String name,String pgone,long collegeid,long departmentid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-积分奖项</p>
	 * <p>方法名：findTeacherScoreList</p>
	 * <p>@param competitionid
	 * <p>@param phone
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年7月4日 下午6:00:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findTeacherScoreList(long prizeid,long competitionid,String phone,PageModel pageModel)  throws  Exception;
}
