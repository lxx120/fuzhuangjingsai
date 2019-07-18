package com.match.org.service;

import java.util.List;
import java.util.Map;

import com.match.org.model.Department;

public interface DepartmentService {

	/**
	 * 
	 * <p>功能描述：根据学校id查询院系</p>
	 * <p>方法名：findDepartmentList</p>
	 * <p>@param department
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月3日 下午5:28:42</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findDepartmentList(Department department)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：添加院系</p>
	 * <p>方法名：addDepartment</p>
	 * <p>@param department
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月21日 下午3:28:34</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addDepartment(Department department)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改院系</p>
	 * <p>方法名：updateDepartment</p>
	 * <p>@param department
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月21日 下午3:29:27</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateDepartment(Department department)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除院系</p>
	 * <p>方法名：deleteDepartment</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月21日 下午3:29:37</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteDepartment(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询详情</p>
	 * <p>方法名：findDepartmentById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月23日 下午5:19:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object> findDepartmentById(long id)  throws  Exception;
}
