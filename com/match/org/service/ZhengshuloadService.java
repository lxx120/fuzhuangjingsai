package com.match.org.service;

import java.util.List;
import java.util.Map;

import com.match.org.model.Zhengshuload;

public interface ZhengshuloadService {

	/**
	 * 
	 * <p>功能描述：添加证书</p>
	 * <p>方法名：addZhengshu</p>
	 * <p>@param zhengshuload
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月13日 上午11:18:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addZhengshu(Zhengshuload zhengshuload)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改证书</p>
	 * <p>方法名：updateZhengshu</p>
	 * <p>@param zhengshuload
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月13日 上午11:18:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateZhengshu(Zhengshuload zhengshuload)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询证书</p>
	 * <p>方法名：finfzhengshu</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月13日 上午11:18:31</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object> finfzhengshu(long id)  throws  Exception;
	
	public  List<Map<String,Object>>  findzhengshulist(long competitionid)  throws  Exception;
}
