package com.match.proposition.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Intermatchperson;

@Mapper
public interface IntermatchpersonMapper {

	/**
	 * 
	 * <p>功能描述：是否存在</p>
	 * <p>方法名：findExist</p>
	 * <p>@param userid
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月5日 下午3:46:25</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from intermatchperson where personid=#{userid} and competitionid=#{competitionid}")
	public  Map<String,Object> findExist(@Param("userid") long userid,@Param("competitionid") long competitionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加</p>
	 * <p>方法名：addIntermatchperson</p>
	 * <p>@param intermatchperson
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月5日 下午3:46:17</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addIntermatchperson(Intermatchperson intermatchperson)  throws  Exception;
}
