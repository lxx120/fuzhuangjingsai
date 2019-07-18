package com.match.org.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.College;

public interface CollegeService {

	
	/**
	 * 
	 * <p>功能描述：添加学校</p>
	 * <p>方法名：addCollege</p>
	 * <p>@param college
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年3月22日 下午2:45:27</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  addCollege(College college,String phone)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：删除学校</p>
	 * <p>方法名：deleteCollege</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年3月22日 下午2:45:16</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  deleteCollege(long id)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：修改学校</p>
	 * <p>方法名：updateCollege</p>
	 * <p>@param college
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年3月22日 下午2:45:56</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateCollege(long userid,College college)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询学校（分页）</p>
	 * <p>方法名：findCollegePage</p>
	 * <p>@param college
	 * <p>@param pm
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年3月22日 下午2:47:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findCollegePage(College college,PageModel pm)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询学校详情</p>
	 * <p>方法名：findCollegeById</p>
	 * <p>@param college
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年3月22日 下午2:48:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findCollegeById(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校负责人修改学校</p>
	 * <p>方法名：updateCollegeByID</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年5月20日 下午4:32:06</p>  
	 * <p>创建者：lxx</p>
	 */
	public  boolean  updateCollegeByID(College  college)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询学校列表</p>
	 * <p>方法名：findCollegeList</p>
	 * <p>@param name
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月3日 下午4:50:25</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findCollegeList(String name)  throws  Exception;

	/**
	 * <p>功能描述：前台 名师名校 的展示（分页）</p>
	 * <p>方法名：findGoodCollegeList</p>
	 * @param college
	 * @param p
	 * @return
	 * <p>创建日期：2019年4月3日 下午</p>  
	 * <p>创建者：zyq</p>
	 * @throws Exception 
	 */
	public PageResult<Map<String, Object>> findGoodCollegeListPage(College college, PageModel p) throws Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学校参赛情况</p>
	 * <p>方法名：findCollegeJoinCompetition</p>
	 * <p>@param competitionid
	 * <p>@param collegeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月21日 下午5:36:21</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findCollegeJoinCompetition(long competitionid,long collegeid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-院校领导查询指导教师</p>
	 * <p>方法名：findCollegeTeacher</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午2:21:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findCollegeTeacher(long competitionid,long enterpriseid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-院校领导查询参赛学生</p>
	 * <p>方法名：findCollegeStudent</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午2:38:19</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findCollegeStudent(String jename,String major,long competitionid,long enterpriseid,PageModel pageModel)  throws  Exception;

	
	/**
	 * 
	 * <p>功能描述：后台-赛区负责人查询赛区所参加的学校</p>
	 * <p>方法名：fingCollegeByDivision</p>
	 * <p>@param name
	 * <p>@param competitionid
	 * <p>@param divisionid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月15日 下午2:58:54</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  fingCollegeByDivision(String name,long competitionid,long divisionid,PageModel pageModel)  throws  Exception;
}
