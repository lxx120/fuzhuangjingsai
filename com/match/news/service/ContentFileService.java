package com.match.news.service;

import java.util.List;
import java.util.Map;

import com.match.news.model.ContentFile;

public interface ContentFileService {

	/**
	 * 
	 * <p>功能描述：添加内容文件</p>
	 * <p>方法名：addContentFile</p>
	 * <p>@param contentFile
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午4:54:11</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addContentFile(ContentFile contentFile)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询内容对应的文件</p>
	 * <p>方法名：findContentFileList</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月25日 下午4:55:14</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findContentFileList(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteContentFile</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午6:08:28</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteContentFile(long id)  throws  Exception;
}
