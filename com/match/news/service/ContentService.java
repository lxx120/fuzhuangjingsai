package com.match.news.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.news.model.Content;
import com.match.news.model.ContentFile;

public interface ContentService {

	/**
	 * 
	 * <p>功能描述：添加新闻</p>
	 * <p>方法名：addContent</p>
	 * <p>@param content
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午2:56:54</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addContent(Content content,ContentFile contentFile)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：删除新闻</p>
	 * <p>方法名：deleteContent</p>
	 * <p>@param content
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午2:57:04</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteContent(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改新闻</p>
	 * <p>方法名：updateContent</p>
	 * <p>@param content
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午2:57:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateContent(Content content,ContentFile contentFile)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：新闻查询（分页）</p>
	 * <p>方法名：findContentPage</p>
	 * <p>@param content
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月25日 下午2:57:10</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findContentPage(Content content,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个新闻</p>
	 * <p>方法名：findContentByID</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月25日 下午2:57:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findContentByID(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个新闻-web</p>
	 * <p>方法名：findContentByID</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月25日 下午2:57:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findContentByIDWeb(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：新闻查询（分页）</p>
	 * <p>方法名：findContentPage</p>
	 * <p>@param content
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月25日 下午2:57:10</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findContentPageWeb(PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改浏览次数</p>
	 * <p>方法名：updateContentViews</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月25日 下午2:57:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateContentViews(long id)  throws  Exception;
}
