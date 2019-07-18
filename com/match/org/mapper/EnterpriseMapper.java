package com.match.org.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.org.model.Enterprise;

@Mapper
public interface EnterpriseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);
    
    List<Map<String,Object>>  findEnterprisePage(@Param("name")  String name,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    Map<String,Object>  findEnterpriseById(long id)  throws Exception;
    
    @Select("select * from enterprise where id=#{id}")
    public  Map<String,Object>  findEnterpriseByIdWeb(long id)  throws  Exception;

	int count(Enterprise exEnterprise)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：赛区负责人查询对应赛区的企业命题</p>
	 * <p>方法名：findDivisionToCompany</p>
	 * <p>@param name
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月1日 下午2:21:41</p>  
	 * <p>创建者：lxx</p>
	 */
	List<Map<String,Object>>  findDivisionToCompany(@Param("competitionid") long competitionid,@Param("id") long id,@Param("name") String name,@Param("page") int page,@Param("pagesize") int pagesize) throws Exception;
	
	int  countfindDivisionToCompany(@Param("competitionid") long competitionid,@Param("id") long id,@Param("name") String name)  throws Exception;
	
	/**
	 * 
	 * <p>功能描述：查询命题的详情以及企业的详情</p>
	 * <p>方法名：findDivisionToCompanyByid</p>
	 * <p>@param themeid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年4月1日 下午3:51:45</p>  
	 * <p>创建者：lxx</p>
	 */
	Map<String,Object> findEnterpriseToTheme(long themeid)  throws  Exception;
	
	
	/**
     * 
     * <p>功能描述：查询企业参加赛事对应的作品（企业前端页面-查询参与命题）</p>
     * <p>方法名：findEnterpriseJoinComThemeWork</p>
     * <p>@param id
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月14日 上午9:55:05</p>  
     * <p>创建者：lxx</p>
     */
    public  List<Map<String,Object>>  findEnterpriseJoinComThemeWork(@Param("divisionid") long divisionid,@Param("id") long id,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    @Select("SELECT count(*) FROM ThemeWork a LEFT JOIN themesignup b ON a.`themesignupid`=b.`id` WHERE enterpriseid=#{divisionid} AND a.competitionid=#{id}")
    public  int  countfindEnterpriseJoinComThemeWork(@Param("divisionid") long divisionid,@Param("id") long id)  throws  Exception;
    
    
    @Select("SELECT DISTINCT b.name,b.`id` FROM theme a LEFT JOIN enterprise b ON a.`enterpriseid`=b.`id` WHERE a.competitionid=#{competitionid}")
    List<Map<String, Object>> findEnterpriseByCompetitionid(long competitionid) throws Exception;
}