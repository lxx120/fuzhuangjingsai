package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.judges.model.JudgeRule;

public interface JudgeRuleService {

	/**
	 * 
	 * <p>功能描述：批量添加轮次规则</p>
	 * <p>方法名：addJudgeRule</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午3:39:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addJudgeRule(List<Map<String,Object>> list)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteJudgeRule</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午3:40:09</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteJudgeRule(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改</p>
	 * <p>方法名：updateJudgeRule</p>
	 * <p>@param judgeRule
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午3:40:18</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateJudgeRule(JudgeRule judgeRule)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询</p>
	 * <p>方法名：findJudgeRuleList</p>
	 * <p>@param arid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月28日 下午3:40:26</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findJudgeRuleList(long arid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个详情</p>
	 * <p>方法名：findJudgeRuleInfo</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月13日 上午11:38:26</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findJudgeRuleInfo(long id)  throws  Exception;
}
