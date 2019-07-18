package com.match.judges.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface WorkJudgeService {

	/**
	 * 
	 * <p>功能描述：查询评委分配的作品（评委id进行查询，不是用户id）</p>
	 * <p>方法名：findJudgeWork</p>
	 * <p>@param judgeID
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月25日 下午6:22:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findJudgeWork(long judgeID,int status,PageModel page)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-查询作品详情以及打分规则详情</p>
	 * <p>方法名：findWorkRuleInformation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月26日 下午4:23:29</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findWorkRuleInformation(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：后端-查询作品详情</p>
	 * <p>方法名：findWorkRuleInformationManager</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月10日 下午5:55:04</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findWorkRuleInformationManager(long id)  throws Exception;
}
