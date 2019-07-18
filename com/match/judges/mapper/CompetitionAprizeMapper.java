package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.CompetitionAprize;

@Mapper
public interface CompetitionAprizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompetitionAprize record);

    int insertSelective(CompetitionAprize record);

    CompetitionAprize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompetitionAprize record);

    int updateByPrimaryKey(CompetitionAprize record);
    
    @Select("select * from CompetitionAprize where arid=#{arid} order by ctime")
    List<Map<String,Object>>  findCompetitionAprizeMapperMap(long arid)  throws Exception;
    
    @Select("select * from CompetitionAprize where arid=#{id}")
    Map<String,Object>  findCompetitionAprizeById(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchCompetitionAprize</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月2日 下午2:25:04</p>  
     * <p>创建者：lxx</p>
     */
    int  addBatchCompetitionAprize(List<Map<String,Object>> list)  throws  Exception;  
    
    /**
     * 
     * <p>功能描述：查询获奖的奖项</p>
     * <p>方法名：findfindPrizeList</p>
     * <p>@param arid
     * <p>@param divisionid
     * <p>@param competitionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月2日 下午2:59:56</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT name,minPercent,maxPercent,score,comprize FROM competitionaprize WHERE competitionid=#{competitionid} and divisionid=#{divisionid} and arid=#{arid} order by ctime")
    List<Map<String,Object>>  findfindPrizeList(@Param("arid") long arid,@Param("divisionid") long divisionid,@Param("competitionid") long competitionid)  throws Exception;
    
    @Select("select maxPercent,score,name,comprize,id from competitionaprize where competitionid=#{competitionid} and divisionid=#{devisionid} and catype=2 order by ctime")
    List<Map<String,Object>>  findPrizeBYHuoJiang(@Param("competitionid") long competitionid,@Param("devisionid") long devisionid)  throws  Exception;
    
    @Select("SELECT a.`id`,a.`arid`,a.`name` FROM   (SELECT id FROM `assessrounds` WHERE divisionid=#{divisionid} AND competitionid=#{competitionid} AND (STATUS=0 OR STATUS=1)) b  LEFT JOIN `competitionaprize` a ON a.`arid`=b.id")
    List<Map<String,Object>>  findNowPrize(@Param("competitionid") long competitionid,@Param("divisionid") long divisionid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询赛区是否有评奖</p>
     * <p>方法名：findDivisionPrize</p>
     * <p>@param competitionid
     * <p>@param divisionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年7月4日 下午4:12:33</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT b.`id`,b.`name` FROM `assessrounds` a LEFT JOIN `competitionaprize` b ON a.id=b.`arid` WHERE a.`competitionid`=#{competitionid} AND a.`divisionid`=#{divisionid} AND b.`catype`=2 order by b.ctime")
    List<Map<String,Object>> findDivisionPrize(@Param("competitionid") long competitionid,@Param("divisionid") long divisionid)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询总赛的评奖</p>
     * <p>方法名：findZongPrize</p>
     * <p>@param competitionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年7月4日 下午4:17:16</p>  
     * <p>创建者：lxx</p>
     */
    @Select("SELECT b.`id`,b.`name` FROM `assessrounds` a LEFT JOIN `competitionaprize` b ON a.id=b.`arid` WHERE a.`competitionid`=#{competitionid} AND a.`divisionid`=0 AND b.`catype`=2 order by b.ctime")
    List<Map<String,Object>> findZongPrize(@Param("competitionid") long competitionid)  throws Exception;
    
    /**
     * 
     * <p>功能描述：查询学校获奖情况</p>
     * <p>方法名：findHUOjiang</p>
     * <p>@param map
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年7月4日 下午4:28:51</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findHUOjiang(Map<String,Object> map) throws  Exception;
}