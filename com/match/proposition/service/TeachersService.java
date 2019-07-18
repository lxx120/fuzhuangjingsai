package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.baciss.model.Teacher;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

public interface TeachersService {

	/**
	 * <p>功能描述：首页  名校名师 中  优秀教师的分页</p>
	 * <p>方法名：findGoodTeacherPage</p>
	 * @param teacher
	 * @param pageModel
	 * @return
	 * <p>创建日期：2019年4月19日 下午</p>  
	 * <p>创建者：zyq</p>
	 */
	PageResult<Map<String, Object>> findGoodTeacherPage(Teacher teacher, PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端教师页面-查询我的团队</p>
	 * <p>方法名：findTeacherTeam</p>
	 * <p>@param id
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月22日 上午10:42:32</p>  
	 * <p>创建者：lxx</p>
	 */
	PageResult<Map<String,Object>>  findTeacherTeam(long id,long competitionid,PageModel pageModel)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询报名的队伍</p>
	 * <p>方法名：findTeamBuSignID</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月22日 下午2:46:24</p>  
	 * <p>创建者：lxx</p>
	 */
	public List<Map<String,Object>>  findTeamBuSignID(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改报名的队伍</p>
	 * <p>方法名：updateTeamBuSign</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月22日 下午2:46:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateTeamBuSign(List<Map<String,Object>>  list)  throws  Exception;
}
