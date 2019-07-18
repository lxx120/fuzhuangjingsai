package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.ThemeWork;

@Mapper
public interface ThemeWorkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThemeWork record);

    int insertSelective(ThemeWork record);

    ThemeWork selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThemeWork record);

    int updateByPrimaryKeyWithBLOBs(ThemeWork record);

    int updateByPrimaryKey(ThemeWork record);
    
    /**
     * 
     * <p>功能描述：查询学校参加了多少人</p>
     * <p>方法名：findcountPeople</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月11日 下午2:45:20</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT COUNT(*) AS count FROM themework a LEFT JOIN themesignup b ON a.`themesignupid`=b.`id` LEFT JOIN student c ON b.`userid`=c.`userid` LEFT JOIN college d ON c.`college`=d.`id` where d.id=#{id}")
    Map<String,Object> findcountPeople(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询提交的作品</p>
     * <p>方法名：findMyThemeWork</p>
     * <p>@param themesignupid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月11日 下午5:06:02</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT id,title,abstraction,content,worktypecode,twcode FROM themework WHERE themesignupid=#{themesignupid}")
    Map<String,Object>  findMyThemeWork(long themesignupid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：前端-学生页面-我的参赛</p>
     * <p>方法名：findThemeWorkByOwner</p>
     * <p>@param competitionid
     * <p>@param enterpriseid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月15日 下午4:28:17</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findThemeWorkByOwner(@Param("userid") long userid,@Param("competitionid")  long competitionid,@Param("enterpriseid") long enterpriseid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindThemeWorkByOwner(@Param("userid") long userid,@Param("competitionid")  long competitionid,@Param("enterpriseid") long enterpriseid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：前端-学生页面-我的作品</p>
     * <p>方法名：findfindMyWork</p>
     * <p>@param userid
     * <p>@param competitionid
     * <p>@param enterpriseid
     * <p>@param code
     * <p>@param workcode
     * <p>@param pricel
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月15日 下午5:24:50</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findfindMyWork(@Param("prizeid") long prizeid,@Param("userid") long userid,@Param("competitionid")  long competitionid,@Param("enterpriseid") long enterpriseid,@Param("code") String code,@Param("workcode") String workcode,@Param("pricel") String pricel,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindfindMyWork(@Param("prizeid") long prizeid,@Param("userid") long userid,@Param("competitionid")  long competitionid,@Param("enterpriseid") long enterpriseid,@Param("code") String code,@Param("workcode") String workcode,@Param("pricel") String pricel)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：后端-查询所有作品</p>
     * <p>方法名：findAllThemeWorkByCompetitionid</p>
     * <p>@param competitionid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月16日 下午3:01:06</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findAllThemeWorkByCompetitionid(@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindAllThemeWorkByCompetitionid(@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid)  throws  Exception;
    
    public  int  updateBatch(List<ThemeWork> list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：后端-查询赛区对应的参赛作品</p>
     * <p>方法名：findAllThemeWorkByCompetitionidDivi</p>
     * <p>@param divisionid
     * <p>@param title
     * <p>@param code
     * <p>@param competitionid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年6月10日 下午5:07:49</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findAllThemeWorkByCompetitionidDivi(@Param("divisionid") long divisionid,@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindAllThemeWorkByCompetitionidDivi(@Param("divisionid") long divisionid,@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：后端查询晋级的参赛作品-系统管理员</p>
     * <p>方法名：findAllThemeWorkByCompetitionidJinJi</p>
     * <p>@param title
     * <p>@param code
     * <p>@param competitionid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年6月10日 下午5:09:19</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findAllThemeWorkByCompetitionidJinJi(@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindAllThemeWorkByCompetitionidJinJi(@Param("title") String title,@Param("code") String code,@Param("competitionid") long competitionid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：导数据用</p>
     * <p>方法名：findThemeCode</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年6月20日 下午2:26:35</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select id,twcode from themework where competitionid=#{competitionid}")
    List<Map<String,Object>>  findThemeCode(long competitionid)  throws  Exception;
    
    @Select("SELECT a.*, b.`score`, d.`realName`,b.id as wjid FROM ( SELECT b.`id`, a.`wartype`, a.`totalticket`, a.`totalscore`, a.`workid` FROM `worksaccessresult` a LEFT JOIN `assessrounds` b ON a.arid = b.id WHERE a.`workid` = #{id} ORDER BY a.`ctime` DESC LIMIT 0, 1 ) a LEFT JOIN `WorkJudge` b ON a.id = b.`arid` AND a.workid = b.`worksID` LEFT JOIN `judgeinformation` c ON b.`judgeID` = c.`id` LEFT JOIN USER d ON c.`userid` = d.`id`")
    List<Map<String,Object>>  findThemeOverScore(long id)  throws  Exception;
    
    List<Map<String,Object>>  findThemeOverScore1(List<String> ids)  throws  Exception;
    
    List<Map<String,Object>>  findPriceThemeWork(List<String> list)  throws  Exception;
}