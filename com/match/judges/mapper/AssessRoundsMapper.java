package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.AssessRounds;

@Mapper
public interface AssessRoundsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssessRounds record);

    int insertSelective(AssessRounds record);

    AssessRounds selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssessRounds record);

    int updateByPrimaryKey(AssessRounds record);
    
    int insetList(List list)  throws  Exception;
    
    List<Map<String,Object>> findAssessRounds(AssessRounds  assessRounds)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询详情</p>
     * <p>方法名：findAssessRoundsById</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年5月6日 下午7:00:21</p>  
     * <p>创建者：lxx</p>
     */
    Map<String,Object> findAssessRoundsById(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：定时-查询所有未结束的轮次</p>
     * <p>方法名：findAllAssessRounds</p>
     * <p>@param status
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月16日 下午2:25:01</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT DATE_FORMAT(reviewstime, '%Y-%m-%d %H:%i:%s') AS reviewstime,DATE_FORMAT(reviewetime, '%Y-%m-%d %H:%i:%s') AS reviewetime,id,status FROM Assessrounds WHERE STATUS=1 or status=0")
    List<Map<String,Object>>  findAllAssessRounds()  throws  Exception;
    
    /**
     * 
     * <p>功能描述：批量修改</p>
     * <p>方法名：updatebatch</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月16日 下午3:09:36</p>  
     * <p>创建者：lxx</p>
     */
    int  updatebatch(List<AssessRounds> list)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询轮次时间到的以及对应的规则</p>
     * <p>方法名：findOverAssessRounds</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月27日 下午3:49:54</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT a.`id`,a.`competitionid`,a.`divisionid`,b.`catype`,b.`minPercent`,b.`maxPercent` FROM `assessrounds` a LEFT JOIN CompetitionAprize b ON a.id=b.`arid` WHERE  a.`status`=1 AND a.`reviewetime`>NOW()")
    List<Map<String,Object>>  findOverAssessRounds()  throws  Exception;
    
    @Select("SELECT a.generalcomment,DATE_FORMAT(a.reviewetime,'%Y-%m-%d %H:%i:%s') AS reviewetime,a.`id`,a.`competitionid`,a.`divisionid`,a.mark,a.type FROM `assessrounds` a  WHERE  a.id=#{arid}")
    Map<String,Object>  findOverAssessRoundsByArid(long arid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：通过伦次查询所有的比赛</p>
     * <p>方法名：findWorkidByArid</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月27日 下午5:42:42</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findWorkidByArid(long id)  throws  Exception;
    
    Map<String,Object> findNowAssrssRoundId() throws  Exception;
    
    @Select("select id from assessrounds where competitionid=#{competitonid} and  divisionid=#{divisionid} and STATUS=1 or status=0")
    Map<String,Object>  findAR(@Param("competitonid")long competitonid,@Param("divisionid") long divisionid)  throws Exception;
    
    @Select("SELECT b.id,b.`score` FROM `assessrounds` a LEFT JOIN `competitionaprize` b ON a.id=b.`arid` WHERE a.`competitionid`= #{competitionid} AND b.`catype`=2")
    List<Map<String,Object>>  findAllARHuoJiang(long competitionid) throws  Exception;
}