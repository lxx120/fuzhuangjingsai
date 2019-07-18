package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.DivisionCompany;

public interface DivisionCompanyService {

	/**
	 * 
	 * <p>功能描述：赛区负责人-查询未绑定的企业</p>
	 * <p>方法名：findOthersCompanyPage</p>
	 * <p>@param province
	 * <p>@param city
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月24日 下午3:04:38</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findOthersCompanyPage(String province,String city,long divisionid,String name,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：赛区负责人-查询绑定的企业</p>
	 * <p>方法名：findBangDingCompany</p>
	 * <p>@param province
	 * <p>@param city
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月24日 下午3:05:57</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findBangDingCompany(String province,String city,long divisionid,String name,PageModel pageModel)  throws Exception;

	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteDivisionCompany</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年5月24日 下午3:06:21</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteDivisionCompany(long id)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：批量添加</p>
	 * <p>方法名：addBatchDivisionCompany</p>
	 * <p>@param divisionid
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月24日 下午3:06:29</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addBatchDivisionCompany(long divisionid,List<Map<String,Object>> list)  throws Exception;

	/**
	 * 
	 * <p>功能描述:单个添加</p>
	 * <p>方法名：addDivisionCompany</p>
	 * <p>@param divisionid
	 * <p>@param deDivisionCompany
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月27日 下午5:19:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public   int  addDivisionCompany(long competitonid,long divisionid,DivisionCompany deDivisionCompany)  throws Exception;

}
