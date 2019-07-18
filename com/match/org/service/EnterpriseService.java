package com.match.org.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;

public interface EnterpriseService {

	/**
	 * 
	 * <p>功能描述：添加企业</p>
	 * <p>方法名：addEnterprise</p>
	 * <p>@param enterprise
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月22日 下午4:48:50</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addEnterprise(Enterprise enterprise,String phone)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业（分页）</p>
	 * <p>方法名：findEnterprisePage</p>
	 * <p>@param exEnterprise
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月22日 下午4:49:56</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findEnterprisePage(Enterprise exEnterprise,PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业详情</p>
	 * <p>方法名：findEnterpriseById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月22日 下午4:51:36</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findEnterpriseById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业详情-前端</p>
	 * <p>方法名：findEnterpriseByIdWeb</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月13日 下午6:32:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findEnterpriseByIdWeb(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除企业</p>
	 * <p>方法名：deleteEnterprise</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月22日 下午4:51:51</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteEnterprise(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改企业</p>
	 * <p>方法名：updateEnterprise</p>
	 * <p>@param enterprise
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月22日 下午4:52:01</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateEnterprise(Enterprise enterprise,String phone,long userid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端修改企业</p>
	 * <p>方法名：updateEnterpriseWeb</p>
	 * <p>@param enterprise
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月13日 下午6:29:17</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateEnterpriseWeb(Enterprise enterprise)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：赛区负责人查询对应的企业命题</p>
	 * <p>方法名：findDivisionToCompany</p>
	 * <p>@param enterprise
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月1日 下午2:18:33</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findDivisionToCompany(Enterprise enterprise,PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询命题的详情以及企业的详情</p>
	 * <p>方法名：findEnterpriseToTheme</p>
	 * <p>@param enid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月1日 下午3:37:28</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findEnterpriseToTheme(long themeid)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业参加赛事对应的作品（企业前端页面-查询参与命题）</p>
	 * <p>方法名：findEnterpriseJoinComThemeWork</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月14日 上午9:49:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findEnterpriseJoinComThemeWork(long divisionid,long id,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：学生页面-通过比赛查询所参赛的企业</p>
	 * <p>方法名：findEnterpriseByCompetitionid</p>
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月14日 上午11:40:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findEnterpriseByCompetitionid(long competitionid)  throws  Exception;
}
