package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Intermatch;

@Mapper
public interface IntermatchMapper {

	/**
	 * 
	 * <p>功能描述：查询是否获奖将</p>
	 * <p>方法名：findIsPrize</p>
	 * <p>@param themeworkid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月5日 下午3:02:31</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("SELECT b.`catype` FROM  themework a LEFT JOIN `competitionaprize` b ON a.`finalAprizeid`=b.`id` where a.id=#{themeworkid}")
	public  Map<String,Object>  findIsPrize(long themeworkid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询是否存在</p>
	 * <p>方法名：findisExist</p>
	 * <p>@param themeworkid
	 * <p>@param competitionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年7月5日 下午3:18:30</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from intermatch where themeworkid=#{themeworkid} and competitionid=#{competitionid}")
	public  Map<String,Object>  findisExist(@Param("themeworkid") long themeworkid,@Param("competitionid") long competitionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加</p>
	 * <p>方法名：addIntermatch</p>
	 * <p>@param intermatch
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月5日 下午3:19:08</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addIntermatch(Intermatch intermatch)  throws  Exception;
	
	@Select("select vote from intermatch where id=#{id}")
	public  int  findVote(long id)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：修改分数</p>
	 * <p>方法名：updateIntermatch</p>
	 * <p>@param intermatch
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年7月5日 下午3:54:27</p>  
	 * <p>创建者：lxx</p>
	 */
	public int  updateIntermatch(Intermatch intermatch)  throws  Exception;
	
	public  List<Map<String,Object>>  findfindIntermatch(@Param("competitionid") long competitionid,@Param("code") String code,@Param("enterpriseid") long enterpriseid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
	
	public  int  countfindfindIntermatch(@Param("competitionid") long competitionid,@Param("code") String code,@Param("enterpriseid") long enterpriseid)  throws  Exception;
}
