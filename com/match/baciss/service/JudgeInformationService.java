package com.match.baciss.service;

import java.util.Map;

import com.match.baciss.model.JudgeInformation;
import com.match.baciss.model.User;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface JudgeInformationService {

	/**
	 * 
	 * <p>功能描述：添加评委</p>
	 * <p>方法名：addJudgeInformation</p>
	 * <p>@param judgeInformation
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 上午9:52:52</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addJudgeInformation(JudgeInformation judgeInformation,long diid)  throws   Exception;
	
	/**
	 * 
	 * <p>功能描述：删除评委</p>
	 * <p>方法名：deleteJudgeInformation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 上午9:52:56</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  deleteJudgeInformation(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改评委</p>
	 * <p>方法名：updateJudgeInformation</p>
	 * <p>@param judgeInformation
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年3月26日 上午9:52:59</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateJudgeInformation(JudgeInformation judgeInformation)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询评委（分页+高级搜索）</p>
	 * <p>方法名：findJudgeInformationPage</p>
	 * <p>@param judgeInformation
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月26日 上午9:53:02</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findJudgeInformationPage(JudgeInformation judgeInformation,PageModel pageModel,long diid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询评委详情</p>
	 * <p>方法名：findJudgeinformation</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月26日 上午9:53:05</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findJudgeInformationById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：分组时查询未绑定的评委</p>
	 * <p>方法名：findGroupToJudgeInformationPage</p>
	 * <p>@param judgeInformation
	 * <p>@param pageModel
	 * <p>@param diid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月30日 下午4:11:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findGroupToJudgeInformationPage(JudgeInformation judgeInformation,PageModel pageModel,long diid,long groupid)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询企业或者学校的人员去选位评委</p>
	 * <p>方法名：findStaffToJudge</p>
	 * <p>@param name
	 * <p>@param org
	 * <p>@param realname
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月16日 下午6:51:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findStaffToJudge(long divisionid,String name,int org,String realname,long orgid,PageModel pageModel)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询已选择的评委</p>
	 * <p>方法名：findChooseStaff</p>
	 * <p>@param name
	 * <p>@param org
	 * <p>@param realname
	 * <p>@param orgid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月17日 下午2:24:24</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findChooseStaff(long divisionid,String name,int org,String realname,long orgid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端页面-通过用户id查询评委详情</p>
	 * <p>方法名：findJudgeInformationByUserid</p>
	 * <p>@param userid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月10日 下午5:06:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findJudgeInformationByUserid(long userid) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加评委</p>
	 * <p>方法名：addJudgeInformation</p>
	 * <p>@param judgeInformation
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月13日 下午2:29:57</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addJudgeInformation1(JudgeInformation judgeInformation)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：后台-添加之前没有的评委</p>
	 * <p>方法名：addJudgeInformationNO</p>
	 * <p>@param judgeInformation
	 * <p>@param user
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年6月13日 下午2:33:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addJudgeInformationNO(JudgeInformation judgeInformation,User user)  throws  Exception;
}
