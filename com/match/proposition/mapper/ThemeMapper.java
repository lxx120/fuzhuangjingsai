package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Theme;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ThemeMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Theme record);

	int insertSelective(Theme record);

	Theme selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Theme record);

	int updateByPrimaryKeyWithBLOBs(Theme record);

	int updateByPrimaryKey(Theme record);

	/**
	 * 
	 * <p>
	 * 功能描述：查询竞赛
	 * </p>
	 * <p>
	 * 方法名：findTheme
	 * </p>
	 * <p>
	 * @param id
	 * <p>
	 * @return
	 * <p>
	 * @throws Exception
	 * </p>
	 * <p>
	 * 返回类型：Map<String,Object>
	 * </p>
	 * <p>
	 * 创建日期：2019年4月8日 下午4:52:28
	 * </p>
	 * <p>
	 * 创建者：lxx
	 * </p>
	 */
	@Select("SELECT a.id, a.title, a.abstraction, a.content, a.limitPeople , DATE_FORMAT(a.stime, '%Y-%m-%d %H:%i:%s') AS stime , DATE_FORMAT(a.etime, '%Y-%m-%d %H:%i:%s') AS etime , DATE_FORMAT(a.singupsttime, '%Y-%m-%d %H:%i:%s') AS singupsttime , DATE_FORMAT(a.singupendtime, '%Y-%m-%d %H:%i:%s') AS singupendtime, a.production , GROUP_CONCAT(b.ptcode) AS ptcode FROM Theme a LEFT JOIN ThemeType b ON a.id = b.themeid where a.id=#{id}")
	public Map<String, Object> findTheme(long id) throws Exception;

	/**
	 * 
	 * <p>
	 * 功能描述：查询详情
	 * </p>
	 * <p>
	 * 方法名：findThemeInformatation
	 * </p>
	 * <p>
	 * @param id
	 * <p>
	 * @return
	 * <p>
	 * @throws Exception
	 * </p>
	 * <p>
	 * 返回类型：Map<String,Object>
	 * </p>
	 * <p>
	 * 创建日期：2019年4月8日 下午5:33:30
	 * </p>
	 * <p>
	 * 创建者：lxx
	 * </p>
	 */
	@Select("SELECT a.`limitPeople`,a.`divisionid`,a.id, DATE_FORMAT(a.stime, '%Y-%m-%d %H:%i:%s') AS stime , DATE_FORMAT(a.etime, '%Y-%m-%d %H:%i:%s') AS etime , DATE_FORMAT(a.singupsttime, '%Y-%m-%d %H:%i:%s') AS singupsttime , DATE_FORMAT(a.singupendtime, '%Y-%m-%d %H:%i:%s') AS singupendtime,a.status,b.commitstime,b.commitetime,b.themestime,b.themeetime FROM Theme a left join competition b on a.competitionid=b.id  where a.id=#{id}")
	public Map<String, Object> findThemeInformatation(long id) throws Exception;

	/**
	 * 
	 * <p>
	 * 功能描述：查询企业参加过的竞赛（高级搜索）
	 * </p>
	 * <p>
	 * 方法名：findEnterpriseJoinTheme
	 * </p>
	 * <p>
	 * @param id
	 * <p>
	 * @param status
	 * <p>
	 * @return
	 * <p>
	 * @throws Exception
	 * </p>
	 * <p>
	 * 返回类型：List<Map<String,Object>>
	 * </p>
	 * <p>
	 * 创建日期：2019年4月8日 下午6:27:07
	 * </p>
	 * <p>
	 * 创建者：lxx
	 * </p>
	 */
	public List<Map<String, Object>> findEnterpriseJoinTheme(@Param("id") long id) throws Exception;

	public List<Map<String, Object>> findfindEnterpriseTheme(@Param("people") int people, @Param("time") int time,
			@Param("competitionid") long competitionid, @Param("page") int page, @Param("pagesize") int pagesize);

	public List<Map<String, Object>> findfindEnterpriseTheme1(@Param("divisionid") long divisionid,
			@Param("people") int people, @Param("time") int time, @Param("competitionid") long competitionid,
			@Param("page") int page, @Param("pagesize") int pagesize);

	@Select("SELECT MAX(themecode) as themecode FROM theme")
	public Map<String, Object> findMaxThemeCode() throws Exception;

	@Select("SELECT  DATE_FORMAT(stime, '%Y-%m-%d %H:%i:%s') AS stime, DATE_FORMAT(etime, '%Y-%m-%d %H:%i:%s') AS etime, DATE_FORMAT(singupsttime, '%Y-%m-%d %H:%i:%s') AS singupsttime,DATE_FORMAT(singupendtime, '%Y-%m-%d %H:%i:%s') AS singupendtime,status,id FROM theme WHERE STATUS!=3")
	public List<Map<String, Object>> findupdateQuartzBatchTheme() throws Exception;
	
	/**
	 * 
	 * <p>功能描述：批量修改</p>
	 * <p>方法名：batchUpdateTheme</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月16日 下午5:49:56</p>  
	 * <p>创建者：lxx</p>
	 */
	public int batchUpdateTheme(List<Theme>  list)  throws Exception;

	
	public  int  count(Theme theme)   throws  Exception;

	
	List<Map<String, Object>> findThemepage(@Param("competitionid") long competitionid,@Param("page")int page, @Param("pagesize") int pagesize);
	
public  int  count1(Theme theme)   throws  Exception;

	
	List<Map<String, Object>> findThemepage1(@Param("competitionid") long competitionid,@Param("page")int page, @Param("pagesize") int pagesize);
	
	/**
	 * 
	 * <p>功能描述：分组分配作品时所用</p>
	 * <p>方法名：findThemeDistributionPage</p>
	 * <p>@param divisionid
	 * <p>@param competitionid
	 * <p>@param size
	 * <p>@param size1
	 * <p>@return</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月23日 下午2:17:33</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findThemeDistributionPage(@Param("arid") long arid,@Param("divisionid") long divisionid,@Param("competitionid") long competitionid,@Param("size") int size,@Param("size1") int size1,@Param("code") String code);
	
	/**
	 * 
	 * <p>功能描述：查询当前比赛企业出的命题列表</p>
	 * <p>方法名：findThemeByEnterpriseId</p>
	 * <p>@param competitionid
	 * <p>@param enterpriseid
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月15日 上午10:54:07</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findThemeByEnterpriseId(@Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
	
	int  countfindThemeByEnterpriseId(@Param("competitionid") long competitionid,@Param("enterpriseid") long enterpriseid)  throws  Exception;
	
	@Select("SELECT COUNT(*) AS typesumcount,workType,b.name FROM `themesignup` a LEFT JOIN `propositiontype` b ON a.workType = b.code WHERE a.themeid=#{themeid} GROUP BY a.`workType`")
	List<Map<String,Object>>  findthemeGroupByWorktype(long themeid)  throws  Exception;
	
	@Select("SELECT COUNT(*) AS sumcount FROM themesignup WHERE themeid=#{themeid}")
	int  countfindthemeGroupByWorktype(long themeid)  throws  Exception;
	
	List<Map<String,Object>>  findthemeToSchool(@Param("worktype") String worktype,@Param("themeid") long themeid,@Param("page") int page,@Param("pagesize") int pagesize)  throws Exception;
	
	int  countfindthemeToSchool(@Param("worktype") String worktype,@Param("themeid") long themeid)  throws Exception;

	/**
	 * 
	 * <p>功能描述：导入用</p>
	 * <p>方法名：findThemeExist</p>
	 * <p>@param themecode
	 * <p>@param themename
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年6月19日 下午3:25:12</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from theme where themecode=#{themecode} and title=#{themename}")
	Map<String,Object>  findThemeExist(@Param("themecode") String themecode,@Param("themename") String themename)  throws  Exception;
}