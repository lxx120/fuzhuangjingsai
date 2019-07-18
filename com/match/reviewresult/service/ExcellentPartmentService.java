package com.match.reviewresult.service;

import java.util.List;
import java.util.Map;

public interface ExcellentPartmentService {

	/**
	 * 
	 * <p>功能描述：查询学校对应的院系的积分</p>
	 * <p>方法名：findAllExcellentPartment</p>
	 * <p>@param competitionid
	 * <p>@param collegeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年6月29日 下午3:48:03</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findAllExcellentPartment(long competitionid,long collegeid)  throws  Exception;
}
