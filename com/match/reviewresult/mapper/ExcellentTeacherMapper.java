package com.match.reviewresult.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.ExcellentTeacher;

@Mapper
public interface ExcellentTeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExcellentTeacher record);

    int insertSelective(ExcellentTeacher record);

    ExcellentTeacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcellentTeacher record);

    int updateByPrimaryKey(ExcellentTeacher record);
    
    /**
     * 
     * <p>功能描述：查询优秀老师</p>
     * <p>方法名：findExcellentTeacher</p>
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年6月28日 上午11:11:38</p>  
     * <p>创建者：lxx</p>
     */
    List<Map<String,Object>>  findExcellentTeacher(@Param("page")  int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    int  countfindExcellentTeacher()  throws  Exception;
    
    @Select("select count(*) from excellentteacher where competationid=#{competitonid}")
    int  countSumTeacher(long competitonid)  throws  Exception;
    
    public  int addbatchExcellentTeacher(List<ExcellentTeacher> list)  throws  Exception;
    
    public  List<Map<String,Object>>  findAllTeacherScore(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid)  throws  Exception;

    public  List<Map<String,Object>>  findAllTeacherScorePage(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;

    public  int  countfindAllTeacherScorePage(@Param("competitionid") long competitionid,@Param("collegeid") long collegeid)  throws  Exception;

}