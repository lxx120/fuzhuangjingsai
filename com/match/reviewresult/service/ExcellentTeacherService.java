package com.match.reviewresult.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.reviewresult.model.ExcellentTeacher;

public interface ExcellentTeacherService {

	/**
	 * 
	 * <p>功能描述：添加优秀老师</p>
	 * <p>方法名：addExcellentTeacher</p>
	 * <p>@param excellentTeacher
	 * <p>@throws Exception</p>
	 * <p>返回类型：void</p>
	 * <p>创建日期：2019年6月28日 上午10:19:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  addExcellentTeacher(ExcellentTeacher excellentTeacher)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除优秀老师</p>
	 * <p>方法名：deleteExcellentTeacher</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年6月28日 上午10:19:54</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteExcellentTeacher(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询优秀教师</p>
	 * <p>方法名：findExcellentTeacherPage</p>
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月28日 上午11:10:02</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findExcellentTeacherPage(PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：教师积分</p>
	 * <p>方法名：findAllTeacherScorePage</p>
	 * <p>@param competitionid
	 * <p>@param collegeid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月29日 下午4:19:40</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findAllTeacherScorePage(long competitionid,long collegeid,PageModel pageModel)  throws Exception;

}
