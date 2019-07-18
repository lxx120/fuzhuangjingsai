package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.proposition.model.ChaXunTiaoJian1;
import com.match.proposition.model.ThemeWork;

public interface ThemeWorkService {

	/**
	 * 
	 * <p>功能描述：添加学生作品</p>
	 * <p>方法名：addThemeWork</p>
	 * <p>@param themeWork
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月11日 上午9:38:23</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int addThemeWork(ThemeWork themeWork) throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改学生作品</p>
	 * <p>方法名：updateThemeWork</p>
	 * <p>@param themeWork
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月11日 下午4:24:57</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateThemeWork(ThemeWork themeWork)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询提交的作品</p>
	 * <p>方法名：findMyThemeWork</p>
	 * <p>@param themesignupid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月11日 下午5:00:31</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findMyThemeWork(long themesignupid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学生页面-我的参赛</p>
	 * <p>方法名：findThemeWorkByOwner</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月15日 下午4:22:54</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findThemeWorkByOwner(long userid,long competitionid,long enterpriseid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：前端-学生页面-我的作品</p>
	 * <p>方法名：findMyWork</p>
	 * <p>@param userid
	 * <p>@param chaXunTiaoJian1
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月15日 下午5:21:53</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findMyWork(long userid,ChaXunTiaoJian1 chaXunTiaoJian1,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：后端-查询所有作品</p>
	 * <p>方法名：findAllThemeWorkByCompetitionid</p>
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月16日 下午2:58:45</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findAllThemeWorkByCompetitionid(String title,String code,long competitionid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：后端-查询赛区对应的参赛作品</p>
	 * <p>方法名：findAllThemeWorkByCompetitionid</p>
	 * <p>@param divisionid
	 * <p>@param title
	 * <p>@param code
	 * <p>@param competitionid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月10日 下午5:04:33</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findAllThemeWorkByCompetitionidDivi(long divisionid,String title,String code,long competitionid,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：后端查询晋级的参赛作品-系统管理员</p>
	 * <p>方法名：findAllThemeWorkByCompetitionidJinJi</p>
	 * <p>@param divisionid
	 * <p>@param title
	 * <p>@param code
	 * <p>@param competitionid
	 * <p>@param pageModel
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月10日 下午5:05:49</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findAllThemeWorkByCompetitionidJinJi(String title,String code,long competitionid,PageModel pageModel)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询作品最终得分-后台用</p>
	 * <p>方法名：findThemeOverScore</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月27日 上午11:15:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findThemeOverScore(long id)  throws  Exception;
}
