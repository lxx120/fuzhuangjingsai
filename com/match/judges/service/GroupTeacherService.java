package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.judges.model.GroupTeacher;

public interface GroupTeacherService {

	
	/**
	 * 
	 * <p>功能描述：添加分组评委</p>
	 * <p>方法名：addGroupTeacher</p>
	 * <p>@param groupTeacher
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月30日 上午11:05:58</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addGroupTeacher(GroupTeacher  groupTeacher)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询分组对应的评委</p>
	 * <p>方法名：findGroupTeacherList</p>
	 * <p>@param groupid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月30日 上午11:16:20</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findGroupTeacherList(long groupid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteGroupTeacher</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月30日 上午11:21:40</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteGroupTeacher(long id,long divisionid,long groupid,long arid,long teacherid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：批量修改评委权重</p>
	 * <p>方法名：updateBatchGroupTeacher</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月30日 下午2:17:32</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateBatchGroupTeacher(List<Map<String,Object>>  list)  throws  Exception;
}
