package com.match.reviewresult.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.ExcellentCollege;

@Mapper
public interface ExcellentCollegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcellentCollege record);

    int insertSelective(ExcellentCollege record);

    ExcellentCollege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcellentCollege record);

    int updateByPrimaryKey(ExcellentCollege record);
    
    public  int addbatchExcellentCollege(List<ExcellentCollege> list)  throws  Exception;
    
    @Select("SELECT a.`name`,IFNULL(b.`score`,0) AS score  FROM college a LEFT JOIN `collegescore` b ON a.id=b.`collegeid` where b.`competationid`=#{competitionid} order by b.score desc ")
    public  List<Map<String,Object>>  findAllCollege(@Param("competitionid") long competitionid)  throws  Exception;
    
    @Select("SELECT a.`name`,IFNULL(b.`score`,0) AS score,a.id FROM college a LEFT JOIN `collegescore` b ON a.id=b.`collegeid` where b.`competationid`=#{competitionid} order by b.score desc limit ${page},#{pagesize}")
    public  List<Map<String,Object>>  findAllCollegePage(@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    @Select("SELECT count(*)  FROM college a LEFT JOIN `collegescore` b ON a.id=b.`collegeid` where b.`competationid`=#{competitionid}")
    public  int  countfindAllCollegePage(@Param("competitionid") long competitionid)  throws  Exception;
    
    @Select("SELECT b.`name`,b.`collegephoto`,b.`id` FROM `excellentcollege` a LEFT JOIN college b ON a.`collegeid`=b.`id` WHERE a.`competationid`=#{competitionid} ORDER BY a.`totalcsore` DESC LIMIT 0,20")
    public  List<Map<String,Object>>  findExcellentCollege(long competitionid)  throws  Exception;
}