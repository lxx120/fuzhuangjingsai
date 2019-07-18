package com.match.reviewresult.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.ExcellentPartment;

@Mapper
public interface ExcellentPartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcellentPartment record);

    int insertSelective(ExcellentPartment record);

    ExcellentPartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcellentPartment record);

    int updateByPrimaryKey(ExcellentPartment record);
    
    public  int addbatchExcellentPartment(List<ExcellentPartment> list)  throws  Exception;
    
    @Select("SELECT a.`name`,b.`dename`,IFNULL(c.`score`,0) AS score FROM college a LEFT JOIN department b ON a.id=b.`collegeid` LEFT JOIN `departmentscore` c ON b.`id`=c.`departmentid` where a.`id`=#{collegeid} AND c.`competationid`=#{competitionid} order by c.score desc")
    public  List<Map<String,Object>>  findAllExcellentPartment(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid)  throws  Exception;

}