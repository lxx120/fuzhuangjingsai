package com.match.proposition.service;

import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.Intermatch;
import com.match.proposition.model.Intermatchperson;

public interface IntermatchSerice {

	/**
	 * 
	 * <p>功能描述：学生参加网络复活比赛</p>
	 * <p>方法名：addIntermatch</p>
	 * <p>@param intermatch
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月5日 下午2:49:37</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  addIntermatch(Intermatch intermatch)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：投票</p>
	 * <p>方法名：updateIntermatch</p>
	 * <p>@param intermatch
	 * <p>@param intermatchperson
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月5日 下午2:51:09</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  updateIntermatch(Intermatchperson intermatchperson)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询</p>
	 * <p>方法名：findIntermatch</p>
	 * <p>@param competitionid
	 * <p>@param code
	 * <p>@param enterpriseid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年7月5日 下午2:51:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public PageResult<Map<String,Object>>  findIntermatch(String code,long enterpriseid,PageModel pageModel)  throws  Exception;

	
}
