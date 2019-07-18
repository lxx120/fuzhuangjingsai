package com.match.reviewresult.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.reviewresult.model.ExcellentThemeWork;

public interface ExcellentThemeWorkService {

	/**
	 * 
	 * <p>功能描述：批量添加</p>
	 * <p>方法名：addBatchExcellentThemeWork</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月3日 上午11:13:12</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addBatchExcellentThemeWork(long competitionid,List<Map<String,Object>>  list)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：删除</p>
	 * <p>方法名：deleteExcellentThemeWork</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月3日 上午11:14:34</p>  
	 * <p>创建者：lxx</p>
	 */
	public int deleteExcellentThemeWork(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述:查询优秀作品</p>
	 * <p>方法名：findExcellentThemeWork</p>
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年7月3日 上午11:14:54</p>  
	 * <p>创建者：lxx</p>
	 */
	public List<Map<String,Object>> findExcellentThemeWork()  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询没有被选为优秀作品的</p>
	 * <p>方法名：findOtherThemeWork</p>
	 * <p>@param competitionid
	 * <p>@param stwcode
	 * <p>@param code
	 * <p>@param phone
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年7月3日 下午6:09:21</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findOtherThemeWork(long competitionid,String stwcode,String code,String phone,PageModel pageModel)  throws  Exception;

}
