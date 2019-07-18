package com.match.reviewresult.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.ExcellentPerson;

@Mapper
public interface ExcellentPersonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcellentPerson record);

    int insertSelective(ExcellentPerson record);

    ExcellentPerson selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcellentPerson record);

    int updateByPrimaryKey(ExcellentPerson record);
    
    public  int addbatchExcellentPerson(List<ExcellentPerson> list)  throws  Exception;
    
    @Select("SELECT a.`score`,b.`realName`,d.`name`,e.`dename`,b.phone FROM `personalscore`  a LEFT JOIN USER b ON a.`userid`=b.`id` LEFT JOIN student c ON b.`id`=c.`userid` LEFT JOIN college d ON c.`college`=d.`id` LEFT JOIN department e ON c.`department`=e.`id` where a.competationid=#{competitionid} order by a.score desc")
    List<Map<String,Object>>  findAllExcellentPerson(long competitionid)  throws  Exception;
    
    @Select("SELECT a.`score`,b.`realName`,d.`name`,e.`dename`,b.phone FROM `personalscore`  a LEFT JOIN USER b ON a.`userid`=b.`id` LEFT JOIN student c ON b.`id`=c.`userid` LEFT JOIN college d ON c.`college`=d.`id` LEFT JOIN department e ON c.`department`=e.`id` where a.competationid=${competitionid} order by a.score desc limit ${page},${pagesize}")
    List<Map<String,Object>> findExcellentPersonPage(@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize) throws  Exception;
    
    @Select("SELECT count(*) FROM `personalscore`  a LEFT JOIN USER b ON a.`userid`=b.`id` LEFT JOIN student c ON b.`id`=c.`userid` LEFT JOIN college d ON c.`college`=d.`id` LEFT JOIN department e ON c.`department`=c.`id` where a.competationid=${competitionid}")
    int countExcellentPersonPage(@Param("competitionid") long competitionid) throws  Exception;
}