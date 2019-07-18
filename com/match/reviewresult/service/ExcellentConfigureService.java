package com.match.reviewresult.service;

import java.util.Map;

import com.match.reviewresult.model.ExcellentConfigure;

public interface ExcellentConfigureService {

	/**
	 * 
	 * <p>功能描述：查询优秀配置</p>
	 * <p>方法名：findExcellentConfigure</p>
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月28日 下午3:12:04</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findExcellentConfigure(long competitionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加优秀配置</p>
	 * <p>方法名：addExcellentConfigure</p>
	 * <p>@param excellentConfigure
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年6月28日 下午3:12:46</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addExcellentConfigure(ExcellentConfigure  excellentConfigure)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改优秀配置</p>
	 * <p>方法名：updateExcellentConfigure</p>
	 * <p>@param excellentConfigure
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年6月28日 下午3:13:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateExcellentConfigure(ExcellentConfigure excellentConfigure)  throws  Exception;
}
