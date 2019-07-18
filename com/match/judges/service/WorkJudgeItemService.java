package com.match.judges.service;

import com.match.judges.model.WorkJudgeItem;

public interface WorkJudgeItemService {

	/**
	 * 
	 * <p>功能描述：添加评委评判表</p>
	 * <p>方法名：addWorkJudgeItem</p>
	 * <p>@param workJudgeItem
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月26日 下午6:03:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addWorkJudgeItem(WorkJudgeItem workJudgeItem,long status)  throws  Exception;
}
