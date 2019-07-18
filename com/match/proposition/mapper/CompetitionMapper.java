package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Competition;

@Mapper
public interface CompetitionMapper {
    int deleteByPrimaryKey(Long id) throws Exception;

    int insert(Competition record) throws Exception;

    public int insertSelective(Competition record) throws Exception;

    Competition selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(Competition record)  throws Exception;

    int updateByPrimaryKeyWithBLOBs(Competition record) throws Exception;

    int updateByPrimaryKey(Competition record) throws Exception;
    
    List<Map<String,Object>>  findCompetitionPage(@Param("name") String name,@Param("page")  int page,@Param("pagesize")  int  pagesize)  throws  Exception;
    
    Map<String,Object>  findCompetitionById(long id)  throws  Exception;
    
    int  count  (Competition competition)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询当前的比赛</p>
     * <p>方法名：findCurrentCompetition</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年3月28日 下午2:41:21</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT b.mark,a.*,b.`id` AS arid,DATE_FORMAT(b.`reviewetime`,'%Y-%m-%d %H:%i:%s') AS reviewetime FROM (SELECT a.id,MAX(b.`level`) AS maxlevel,commitetime FROM Competition a LEFT JOIN Assessrounds b ON a.`id`=b.`competitionid` AND b.`divisionid`=#{divisionid} WHERE a.STATUS = 1 or a.STATUS = 0) a LEFT JOIN Assessrounds b ON a.id=b.`competitionid` AND a.maxlevel=b.`level` AND b.`divisionid`=#{divisionid}")
    Map<String,Object>  findCurrentCompetition(long divisionid)  throws Exception;
    
    /**
     * 
     * <p>功能描述：获取当前正在进行比赛以及对应的伦次</p>
     * <p>方法名：findCurrentCompetition1</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年3月29日 下午4:03:37</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT a.`id` AS comid,b.`id` AS arid,DATE_FORMAT(b.`reviewetime`,'%Y-%m-%d %H:%i:%s') AS reviewetime,DATE_FORMAT(b.`reviewstime`,'%Y-%m-%d %H:%i:%s') AS reviewstime FROM competition a LEFT JOIN assessrounds b ON a.`id`=b.`competitionid` WHERE (a.`status`=1 or a.`status`=0) AND (b.`status`=1 or  b.`status`=0) and divisionid=#{division}")
    Map<String,Object>  findCurrentCompetition1(long divisionid)  throws Exception;
    
    /**
     * 
     * <p>功能描述：只查询当前的竞赛id</p>
     * <p>方法名：findCurrnetCompetition2</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月1日 下午2:27:08</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select id,etime from competition where status=1")
    Map<String,Object>  findCurrnetCompetition2()  throws  Exception;
    
    @Select("SELECT intermatchstime,intermatchetime,name,id,etime,stime,status,commitstime,commitetime,themestime,themeetime FROM competition WHERE STATUS=1 OR STATUS=0")
    Map<String,Object>  findCurrnetCompetition3()  throws  Exception;
    
    @Select("SELECT name,id,etime,stime,status,commitstime,commitetime,themestime,themeetime FROM competition WHERE STATUS=2 order by ctime desc limit 0,1")
    Map<String,Object>  findCurrnetCompetition4()  throws  Exception;
    
    @Select("SELECT name,id FROM competition WHERE STATUS=2 order by ctime desc limit ${page},#{pagesize}")
    List<Map<String,Object>>  findCurrnetCompetition5(@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    @Select("SELECT count(*) FROM competition WHERE STATUS=2")
    int  countfindCurrnetCompetition5()  throws  Exception;
    
    @Select("SELECT DATE_FORMAT(stime,'%Y-%m-%d %H:%i:%s') AS stime,DATE_FORMAT(etime,'%Y-%m-%d %H:%i:%s') AS etime FROM competition where id=#{id}")
    Map<String,Object>  findCurrnetCompetitionTime(long id)  throws  Exception;
    
    @Select("select name,id from Competition order by ctime")
    List<Map<String,Object>>  fidnAllCompetition()  throws  Exception;
    
    /**
     * 
     * <p>功能描述：登录时查询企业所对应的赛区</p>
     * <p>方法名：findUserInfoCompany</p>
     * <p>@param enterpriseid
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年5月8日 下午3:51:14</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT c.`divisionid` FROM Competition a LEFT JOIN Division b ON a.`id`=b.`competitionid` LEFT JOIN DivisionCompany c ON b.id=c.`divisionid` WHERE a.`id`=#{id} AND  c.`companyid`=#{enterpriseid}")
    public Map<String,Object>  findUserInfoCompany(@Param("enterpriseid") long enterpriseid,@Param("id") long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：登录时查询学校所对应的赛区</p>
     * <p>方法名：findUserInfoSchool</p>
     * <p>@param enterpriseid
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年5月8日 下午4:04:34</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT c.`divisionid` FROM Competition a LEFT JOIN Division b ON a.`id`=b.`competitionid` LEFT JOIN DivisionSchool c ON b.id=c.`divisionid` WHERE a.`id`=#{id} AND  c.`schoolid`=#{enterpriseid}")
    public Map<String,Object>  findUserInfoSchool(@Param("enterpriseid")long enterpriseid,@Param("id")long id)  throws  Exception;

    /**
     * 
     * <p>功能描述：每个比赛的获奖名单</p>
     * <p>方法名：findComWinList</p>
     * <p>@param competitionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年7月4日 下午3:10:25</p>  
     * <p>创建者：lxx</p>
     */
    public  List<Map<String,Object>>  findComWinList(long competitionid)  throws  Exception;
}