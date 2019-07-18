package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.Competition;

public interface CompetitionService {

	/**
	 * 
	 * <p>功能描述：添加竞赛</p>
	 * <p>方法名：addCompetition</p>
	 * <p>@param competition
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 下午4:57:32</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int addCompetition(Competition competition,List<Map<String,Object>> list1)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改竞赛</p>
	 * <p>方法名：updateCompetition</p>
	 * <p>@param competition
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 下午4:57:35</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateCompetition(Competition competition)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除竞赛</p>
	 * <p>方法名：deleteCompetition</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 下午4:57:40</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteCompetition(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询竞赛（分页）</p>
	 * <p>方法名：findCompetitionPage</p>
	 * <p>@param competition
	 * <p>@param pageModel
	 * <p>@return</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月26日 下午4:57:44</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findCompetitionPage(Competition competition,PageModel pageModel)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个竞赛</p>
	 * <p>方法名：findCompetitionById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月26日 下午4:57:47</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findCompetitionById(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询所有的竞赛</p>
	 * <p>方法名：fidnAllCompetition</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月11日 上午9:52:01</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  fidnAllCompetition()  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：定时修改比赛状态</p>
	 * <p>方法名：updateCompetitionStatus</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月22日 上午10:12:37</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean   updateCompetitionStatus()  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-查询获奖名单</p>
	 * <p>方法名：findRewardedList</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月4日 下午2:45:27</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findRewardedList(PageModel pageModel) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-查询获奖名单详情</p>
	 * <p>方法名：findfindRewardedListInfo</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月5日 下午5:26:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object> findfindRewardedListInfo(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：学校获奖情况</p>
	 * <p>方法名：findSchoolRewardedList</p>
	 * <p>@param competitionid
	 * <p>@param divisionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月4日 下午4:08:25</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findSchoolRewardedList(long enterpriseid,long competitionid,long divisionid)  throws  Exception;
}
