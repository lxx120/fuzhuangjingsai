package com.match.org.service;

import java.util.List;
import java.util.Map;

import com.match.org.model.DepartMajor;
import com.sun.javafx.geom.Edge;

public interface DepartMajorService {

	/**
	 * 
	 * <p>功能描述：根据院系查询专业</p>
	 * <p>方法名：findDepartMajorList</p>
	 * <p>@param departMajor
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月3日 下午6:34:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>> findDepartMajorList(DepartMajor departMajor)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加专业</p>
	 * <p>方法名：addDepartMajor</p>
	 * <p>@param departMajor
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年5月21日 下午4:06:46</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addDepartMajor(DepartMajor departMajor) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改专业</p>
	 * <p>方法名：updateDepartMajor</p>
	 * <p>@param departMajor
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年5月21日 下午4:07:21</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateDepartMajor(DepartMajor departMajor)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除专业</p>
	 * <p>方法名：deleteDepartMajor</p>
	 * <p>@param departMajor
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年5月21日 下午4:07:49</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteDepartMajor(DepartMajor departMajor)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询详情</p>
	 * <p>方法名：findDepartMajorByid</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月23日 下午5:26:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>   findDepartMajorByid(long id)  throws  Exception;
}
