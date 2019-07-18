package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.judges.model.AssessRounds;

public interface AssessRoundsService {

	/**
	 * 
	 * <p>功能描述：添加轮次</p>
	 * <p>方法名：addAssessRounds</p>
	 * <p>@param assessRounds
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 上午11:35:06</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addAssessRounds(AssessRounds  assessRounds,long divisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改伦次</p>
	 * <p>方法名：updateAssessRounds</p>
	 * <p>@param assessRounds
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 上午11:35:20</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateAssessRounds(AssessRounds  assessRounds,long divisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除伦次</p>
	 * <p>方法名：deleteAssessRounds</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 上午11:35:57</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteAssessRounds(long id,long divisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询伦次（分页）</p>
	 * <p>方法名：findAssessRoundsPage</p>
	 * <p>@param assessRounds
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月28日 上午11:36:00</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findAssessRoundsPage(AssessRounds assessRounds,long divisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个</p>
	 * <p>方法名：findAssessRoundsById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月6日 下午7:04:30</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findAssessRoundsById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：定时去修改</p>
	 * <p>方法名：updateQuartzAssessRounds</p>
	 * <p>@throws Exception</p>
	 * <p>返回类型：void</p>
	 * <p>创建日期：2019年4月16日 下午2:22:12</p>  
	 * <p>创建者：lxx</p>
	 */
	public  void  updateQuartzAssessRounds()  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：轮次结束后去分配(定时暂时不用)</p>
	 * <p>方法名：addDistribution</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月27日 下午3:47:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addDistribution()  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：轮次结束后去分配</p>
	 * <p>方法名：addDistributionByArid</p>
	 * <p>@param arid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月28日 下午4:16:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  addDistributionByArid(String code1,long arid,PageModel pageModel)   throws  Exception;
}
