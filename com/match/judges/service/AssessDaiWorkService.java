package com.match.judges.service;

import java.util.List;
import java.util.Map;

import com.match.judges.model.AssessDaiWork;

public interface AssessDaiWorkService {

	/**
	 * 
	 * <p>功能描述：分配页面查询每组个数</p>
	 * <p>方法名：findAssessDaiWork</p>
	 * <p>@param assessDaiWork
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年3月29日 下午3:19:28</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findAssessDaiWork(AssessDaiWork assessDaiWork)  throws  Exception;
	
	
}
