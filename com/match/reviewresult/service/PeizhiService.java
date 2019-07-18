package com.match.reviewresult.service;

import java.util.Map;

import com.match.reviewresult.model.Peizhi;

public interface PeizhiService {

	/**
	 * 
	 * <p>功能描述：添加或者修改</p>
	 * <p>方法名：addPeizhi</p>
	 * <p>@param peizhi
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月3日 上午11:03:37</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  addPeizhi(Peizhi peizhi)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询</p>
	 * <p>方法名：findPeizhi</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月10日 下午5:47:59</p>  
	 * <p>创建者：lxx</p>
	 */
	public Map<String,Object>  findPeizhi(int type)  throws  Exception;
}
