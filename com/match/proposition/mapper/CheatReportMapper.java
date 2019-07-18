package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.CheatReport;

@Mapper
public interface CheatReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheatReport record);

    int insertSelective(CheatReport record);

    CheatReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheatReport record);

    int updateByPrimaryKeyWithBLOBs(CheatReport record);

    int updateByPrimaryKey(CheatReport record);
    
    @Select("select a.ctime,a.content,a.website,a.crtype,b.realName from cheatreport a left join user b on a.reporterid=b.id where themeworkid=#{themewordid}")
    List<Map<String,Object>>  findCheatByThemeid(long themewordid)  throws  Exception;
    
    
}