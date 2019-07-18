package com.match.news.service;

import java.util.List;
import java.util.Map;

import com.match.news.model.Category;

public interface CategoryService {

	/**
	 * 
	 * <p>功能描述：添加新闻分类</p>
	 * <p>方法名：addCategory</p>
	 * <p>@param category
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 上午10:33:31</p>  
	 * <p>创建者：lxx</p>
	 */
	public   boolean  addCategory(Category  category) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除新闻分类</p>
	 * <p>方法名：deleteCategory</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 上午10:34:05</p>  
	 * <p>创建者：lxx</p>
	 */
	public   boolean  deleteCategory(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：修改新闻分类</p>
	 * <p>方法名：updateCategory</p>
	 * <p>@param category
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 上午10:34:50</p>  
	 * <p>创建者：lxx</p>
	 */
	public   boolean  updateCategory(Category category)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询新闻分类通过id</p>
	 * <p>方法名：findCategoryById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月25日 上午10:35:46</p>  
	 * <p>创建者：lxx</p>
	 */
	public   Map<String,Object>  findCategoryById(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询新闻分类（树状图）</p>
	 * <p>方法名：findCategoryList</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月25日 上午10:36:36</p>  
	 * <p>创建者：lxx</p>
	 */
	public   List<Map<String,Object>>  findCategoryList()  throws  Exception;
}
