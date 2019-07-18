package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.judges.model.CompetitionAprize;

public interface CompetitionAprizeService {

	/**
	 * 
	 * <p>功能描述：添加轮次获奖配置</p>
	 * <p>方法名：addCompetitionAprize</p>
	 * <p>@param competitionAprize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午4:55:53</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean addCompetitionAprize(CompetitionAprize competitionAprize)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：删除轮次获奖配置</p>
	 * <p>方法名：deleteCompetitionAprize</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午4:55:59</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean deleteCompetitionAprize(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：TODO</p>
	 * <p>方法名：updateCompetitionAprize</p>
	 * <p>@param competitionAprize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月28日 下午4:56:02</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateCompetitionAprize(CompetitionAprize competitionAprize)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个</p>
	 * <p>方法名：findCompetitionAprize</p>
	 * <p>@param arid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月28日 下午4:56:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findCompetitionAprize(long arid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询单个</p>
	 * <p>方法名：findCompetitionAprizeById</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月6日 下午7:09:02</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findCompetitionAprizeById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：批量添加或将配置</p>
	 * <p>方法名：addBatchCompetitionAprize</p>
	 * <p>@param divisionidid
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月2日 下午2:20:08</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  addBatchCompetitionAprize(long divisionidid,List<Map<String,Object>> list)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询获奖比例等</p>
	 * <p>方法名：findPrizeList</p>
	 * <p>@param divisionidid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月2日 下午2:48:09</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findPrizeList(long divisionid) throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询伦次的获奖配置</p>
	 * <p>方法名：findPrizeBYHuoJiang</p>
	 * <p>@param competitionid
	 * <p>@param devisionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月28日 下午4:39:20</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>   findPrizeBYHuoJiang(long competitionid,long devisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：后台-查询获奖作品</p>
	 * <p>方法名：findHuoJiangThemeWork</p>
	 * <p>@param divisionid
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月26日 下午3:11:58</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>   findHuoJiangThemeWork(String code,long divisionid,long competitionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询比赛为评奖的选项</p>
	 * <p>方法名：findCompetitionPrize</p>
	 * <p>@param enterpriseid
	 * <p>@param competitionid
	 * <p>@param divisionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月4日 下午6:20:24</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findCompetitionPrize(long enterpriseid,long competitionid, long divisionid)  throws Exception;
}
