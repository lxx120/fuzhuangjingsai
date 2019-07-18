package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.baciss.model.User;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;
import com.match.org.model.propositionstrategy;
import com.match.proposition.model.ChaXunTiaoJian;
import com.match.proposition.model.Theme;
import com.match.proposition.model.ThemeType;

public interface ThemeService {
	
	/**
	 * 
	 * <p>功能描述：添加企业命题</p>
	 * <p>方法名：addTheme</p>
	 * <p>@param theme
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月8日 下午3:02:27</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addTheme(Theme theme,ThemeType themeType,propositionstrategy propositionstrategy)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改企业命题</p>
	 * <p>方法名：updateTheme</p>
	 * <p>@param theme
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月8日 下午3:02:30</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateTheme(Theme theme,ThemeType themeType)  throws  Exception;
	
	public boolean  deleteTheme( long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业命题</p>
	 * <p>方法名：findTheme</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月8日 下午3:01:29</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findTheme(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询企业参加过的竞赛（高级搜索）</p>
	 * <p>方法名：findEnterpriseJoinTheme</p>
	 * <p>@param id
	 * <p>@param status
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月8日 下午6:27:07</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findEnterpriseJoinTheme(long id)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：报名时查询企业列表</p>
	 * <p>方法名：findEnterpriseTheme</p>
	 * <p>@param userid
	 * <p>@param chaXunTiaoJian
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年4月9日 下午5:09:26</p>  
	 * <p>创建者：lxx</p>
	 */
	public  PageResult<Map<String,Object>>  findEnterpriseTheme(long userid,ChaXunTiaoJian chaXunTiaoJian,PageModel pageModel)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询是否能够报名成功</p>
	 * <p>方法名：findIsCanSignUp</p>
	 * <p>@param userid
	 * <p>@param themeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月9日 下午6:47:41</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findIsCanSignUp(long userid,long themeid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：定时-修改命题状态</p>
	 * <p>方法名：updateQuartzBatchTheme</p>
	 * <p>@throws Exception</p>
	 * <p>返回类型：void</p>
	 * <p>创建日期：2019年4月16日 下午5:09:01</p>  
	 * <p>创建者：lxx</p>
	 */
	public  void  updateQuartzBatchTheme()  throws   Exception;

	/**
	 * <p>功能描述：首页  命题企业的分页</p>
	 * <p>方法名：updateQuartzBatchTheme</p>
	 * @param theme
	 * @param p
	 * @return
	 * @throws Exception
	 * <p>创建日期：2019年4月19日 下午</p>  
	 * <p>创建者：zyq</p>
	 */
	PageResult<Map<String,Object>>  findThemepage(Theme theme,PageModel p) throws  Exception;
	
	PageResult<Map<String,Object>>  findThemepage1(Theme theme,PageModel p) throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询企业参当前赛事提交的命题</p>
	 * <p>方法名：findThemeByEnterpriseId</p>
	 * <p>@param enterpriseid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年5月15日 上午10:45:50</p>  
	 * <p>创建者：lxx</p>
	 */
	PageResult<Map<String,Object>>  findThemeByEnterpriseId(long competitionid,long enterpriseid,PageModel pageModel)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：前端-共有多少组选择，其中各种作品类别分别有多少组</p>
	 * <p>方法名：findthemeGroupByWorktype</p>
	 * <p>@param themeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月18日 下午4:34:33</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object>  findthemeGroupByWorktype(long themeid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：分页查询-分别是哪些学校选择</p>
	 * <p>方法名：findthemeToSchool</p>
	 * <p>@param worktype
	 * <p>@param themeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：PageResult<Map<String,Object>></p>
	 * <p>创建日期：2019年6月18日 下午4:44:17</p>  
	 * <p>创建者：lxx</p>
	 */
	PageResult<Map<String,Object>>  findthemeToSchool(String worktype,long themeid,PageModel pageModel)  throws  Exception;
}
