package com.match.org.service;

import java.util.Map;

import com.match.org.model.propositionstrategy;

public interface PropositionstrategyService {

	/**
	 * 
	 * <p>功能描述：添加策略单</p>
	 * <p>方法名：addPropositionstrategy</p>
	 * <p>@param propositionstrategy
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月9日 上午9:48:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addPropositionstrategy(propositionstrategy propositionstrategy) throws Exception;
	
	/**
	 * 
	 * <p>功能描述：修改策略单</p>
	 * <p>方法名：updatePropositionstrategy</p>
	 * <p>@param propositionstrategy
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月9日 上午9:51:12</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updatePropositionstrategy(propositionstrategy propositionstrategy)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业策略单</p>
	 * <p>方法名：findPropositionstrategy</p>
	 * <p>@param enterpriseid
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月9日 上午10:25:57</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findPropositionstrategy(long id)  throws  Exception;
}
