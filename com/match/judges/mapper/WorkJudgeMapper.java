package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.WorkJudge;

@Mapper
public interface WorkJudgeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkJudge record);

    int insertSelective(WorkJudge record);

    WorkJudge selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkJudge record);

    int updateByPrimaryKey(WorkJudge record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchWorkJudge</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月23日 下午3:40:54</p>  
     * <p>创建者：lxx</p>
     */
    int  addBatchWorkJudge(List<WorkJudge>  list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：分组老师删除时联级删除老师被分配的作品</p>
     * <p>方法名：deleteWorkJudgeByTeacher</p>
     * <p>@param teacherid
     * <p>@param divisionid
     * <p>@param arid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月24日 下午2:09:21</p>  
     * <p>创建者：lxx</p>
     */
    @Delete("delete from workjudge where judgeID=#{teacherid} and arid=#{arid} and division=#{divisionid} and groupid=#{groupid} ")
    int  deleteWorkJudgeByTeacher(@Param("teacherid") long teacherid,@Param("divisionid") long divisionid,@Param("arid") long arid,@Param("groupid") long groupid)  throws  Exception;
    
    @Select("SELECT d.picspath,a.`id`,c.`type`,b.title,b.`id` as themeworkid FROM WorkJudge a LEFT JOIN ThemeWork b ON a.`worksID`=b.`id` LEFT JOIN Assessrounds c ON a.`arid`=c.`id` left join themesignup d on b.themesignupid=d.id WHERE a.judgeID=#{judgeID} AND a.competitionid=#{competitionid} and a.judged=#{status} order by a.ctime limit ${page},#{pagesize} ")
    List<Map<String,Object>>  findJudgeWork(@Param("judgeID") long judgeID,@Param("competitionid") long competitionid,@Param("status") int status,@Param("page")  int page,@Param("pagesize") int pagesize)  throws Exception;
    
    @Select("SELECT count(*) FROM WorkJudge a LEFT JOIN ThemeWork b ON a.`worksID`=b.`id` LEFT JOIN Assessrounds c ON a.`arid`=c.`id` WHERE a.judgeID=#{judgeID} AND a.competitionid=#{competitionid} and a.judged=#{status}")
    int  countfindJudgeWork(@Param("judgeID") long judgeID,@Param("competitionid") long competitionid,@Param("status") int status)   throws  Exception;
    
    /**
     * 
     * <p>功能描述：作品命题等详情-前端</p>
     * <p>方法名：findWorkInfo</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月26日 下午4:43:27</p>  
     * <p>创建者：lxx</p>
     */
    public Map<String,Object>  findWorkInfo(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：作品命题等详情-后端</p>
     * <p>方法名：findWorkInfo</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月26日 下午4:43:27</p>  
     * <p>创建者：lxx</p>
     */
    public Map<String,Object>  findWorkInfo1(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询规则详情</p>
     * <p>方法名：fingRuleInfo</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月26日 下午4:46:04</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT 0 as shijiscore,b.type,c.`jrname`,c.score,c.weight,c.id FROM WorkJudge a  LEFT JOIN JudgeRule c ON a.`arid`=c.`arid` left join assessrounds b on a.arid=b.id WHERE a.`id`=#{arid}")
    public  List<Map<String,Object>>  fingRuleInfo(long id)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询一共个评审的个数</p>
     * <p>方法名：findGeShu</p>
     * <p>@param judgeid
     * <p>@param competitionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月26日 下午5:25:55</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT COUNT(*) AS allwork FROM `workjudge` WHERE judgeID=#{s} AND competitionid=#{competitionid} AND judged=#{judged}")
    public  int  findGeShu(@Param("s") long s,@Param("competitionid") long competitionid,@Param("judged") int judged)  throws  Exception;
    
    @Select("select weight from workjudge where id=#{id}")
    public  Double  findWeight(long id)  throws Exception;
    
    @Select("SELECT SUM(score) AS sumscore FROM `workjudge` WHERE worksID=#{workid} and arid=#{arid}")
    public  Double  findWorkSumScore(@Param("workid")long workid,@Param("arid") long arid)  throws  Exception;
    
    @Select("select id from workjudge where judgeID=#{teacherid} and groupid=#{groupid}")
    public  List<Map<String,Object>>  findIdByGTid(@Param("groupid") long groupid,@Param("teacherid") long teacherid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：批量修改</p>
     * <p>方法名：updateBatch</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年6月13日 上午10:59:22</p>  
     * <p>创建者：lxx</p>
     */
    public  int  updateBatch(List<Map<String,Object>> list)  throws  Exception;
}