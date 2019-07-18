package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.baciss.model.Teacher;

@Mapper
public interface TeachersMapper {

	public int count(Teacher teacher) throws  Exception;
	
	public List<Map<String, Object>> findGoodTeacherPage(@Param("page")int page, @Param("pagesize")int pagesize) throws  Exception;

	public List<Map<String,Object>>  findTeacherTeam(@Param("id") long id,@Param("competitionid") long competitionid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
	
	public int  countfindTeacherTeam(@Param("id") long id,@Param("competitionid") long competitionid) throws  Exception;
	
	@Select("select name,id,groupleader,divisionwork,grade,major,phone from team where workid=#{id}")
	public  List<Map<String,Object>>  findTeamBuSignID(long id)  throws  Exception;
	
	public  int  updateTeamBuSign(List<Map<String,Object>>  list)   throws  Exception;
}
