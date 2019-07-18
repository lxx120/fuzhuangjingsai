package com.match.proposition.service;

import java.util.List;
import java.util.Map;

import com.match.proposition.model.ThemeSignup;

public interface ThemeSignupService {

	/**
	 * 
	 * <p>功能描述：报名</p>
	 * <p>方法名：addThemeSignup</p>
	 * <p>@param themeSignup
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月10日 上午10:40:13</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addThemeSignup(ThemeSignup  themeSignup)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询自己报名的比赛</p>
	 * <p>方法名：findMyThemeSignup</p>
	 * <p>@param userid
	 * <p>@param competitionid
	 * <p>@param name
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月10日 下午2:30:35</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findMyThemeSignup(long userid,long competitionid,String name)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：修改报名</p>
	 * <p>方法名：updateThemeSignup</p>
	 * <p>@param themeSignup
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：boolean</p>
	 * <p>创建日期：2019年4月10日 下午3:40:47</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  updateThemeSignup(ThemeSignup themeSignup)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询报名详情</p>
	 * <p>方法名：findThemeSignupInfo</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月10日 下午4:04:14</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findThemeSignupInfo(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：通过报名id查询信息</p>
	 * <p>方法名：findInfoByThemesignId</p>
	 * <p>@param id
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月4日 下午7:03:06</p>  
	 * <p>创建者：lxx</p>
	 */
	public  Map<String,Object>  findInfoByThemesignId(long id)  throws  Exception;
}
