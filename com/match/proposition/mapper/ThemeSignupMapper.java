package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.ThemeSignup;

@Mapper
public interface ThemeSignupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThemeSignup record);

    int insertSelective(ThemeSignup record);

    ThemeSignup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThemeSignup record);

    int updateByPrimaryKey(ThemeSignup record);
    
    /**
     * 
     * <p>功能描述：查询报名人数</p>
     * <p>方法名：findSumThemeSignUpMember</p>
     * <p>@param themeid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月10日 上午10:28:21</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT COUNT(*) as count FROM `themesignup` WHERE themeid=#{themeid} ")
    Map<String,Object> findSumThemeSignUpMember(long themeid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询是否参加过</p>
     * <p>方法名：findIsVisitTheme</p>
     * <p>@param themeid
     * <p>@param userid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月10日 上午10:28:41</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select id from themesignup where themeid=#{themeid} and userid=#{userid}")
    Map<String,Object>  findIsVisitTheme(@Param("themeid") long themeid,@Param("userid") long userid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询自己报名的赛事</p>
     * <p>方法名：findMyThemeSignup</p>
     * <p>@param userid
     * <p>@param competitionid
     * <p>@param name
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月10日 下午2:34:29</p>  
     * <p>创建者：lxx</p>
     */
    public List<Map<String,Object>>  findMyThemeSignup(@Param("userid") long userid,@Param("competitionid") long competitionid,@Param("name") String name)  throws Exception;
    
    public  Map<String,Object>  findThemeSignupInfo(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询各种编号</p>
     * <p>方法名：findAllCode</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月11日 下午2:35:42</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT f.id as collegeid,a.workType,b.`themecode`,c.`code` divisioncode,f.`code` AS collegecode FROM themesignup a LEFT JOIN Theme b ON a.`themeid`=b.`id` LEFT JOIN division c ON a.`divisionid`=c.`id` LEFT JOIN student d ON a.`userid`=d.`userid` LEFT JOIN college f ON d.`college`=f.`id` where a.id=#{id}")
    public  Map<String,Object>  findAllCode(long id) throws Exception;
    
    
    public  Map<String,Object>  findInfoByThemesignId(long id)  throws  Exception;
    
    
    @Select("SELECT a.stwcode,a.stitle,b.`name` AS diname, c.`title`, a.`teacher`, a.`teacherphone`, d.`phone`, d.`qq`, d.`email`, d.`cardID`, f.`name` AS collegename, g.`dename`, c.`title` AS themename, a.`workType`, h.`name` AS typename FROM `themesignup` a LEFT JOIN `division` b ON a.`divisionid` = b.`id` LEFT JOIN theme c ON a.`themeid` = c.`id` LEFT JOIN USER d ON a.`userid` = d.`id` LEFT JOIN student e ON a.`userid` = e.`userid` LEFT JOIN college f ON e.`college`=f.`id` LEFT JOIN `department` g ON e.`department` = g.`id` LEFT JOIN `propositiontype` h ON a.`workType` = h.`code` where a.id=#{id}")
    public  Map<String,Object>  findBaoMingPDFById(long id)  throws Exception;
    
   
}