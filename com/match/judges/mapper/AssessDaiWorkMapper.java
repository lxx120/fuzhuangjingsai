package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.alibaba.dubbo.config.support.Parameter;
import com.match.judges.model.AssessDaiWork;

@Mapper
public interface AssessDaiWorkMapper {

	
	@Select("select a.*, b.name,c.`groupnum`,c.`sumteacher`,c.id as arrid from(SELECT c.`workType` AS `worktypecode`,d.`name` AS typename, count(*) as countnum,a.`competitionid`,a.`divisionid`,a.`arid` FROM `assessdaiworks` a left join `themework` b on a.workid = b.`id` LEFT JOIN `themesignup` c ON b.`themesignupid`= c.`id` LEFT JOIN `propositiontype` d ON c.`workType` = d.`code`  where a.`competitionid`=#{competitionid} and a.`arid`=#{arid} and a.divisionid=#{devisionid} group by c.workType) a LEFT JOIN `propositiontype` b ON a.`worktypecode` = b.`code` LEFT JOIN `assessrole` c ON a.worktypecode=c.`worktypecode` AND a.arid=c.`arid` ORDER BY b.`ctime`")
	public  List<Map<String,Object>>  findAssessDaiWorkList(@Param("competitionid") long competitionid,@Param("arid") long arid,@Param("devisionid") long devisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：添加</p>
	 * <p>方法名：addAssessDaiWork</p>
	 * <p>@param assessDaiWork
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月22日 下午6:21:22</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addAssessDaiWork(AssessDaiWork assessDaiWork)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：批量添加</p>
	 * <p>方法名：addBatchAssessDaiWork</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月7日 下午4:45:00</p>  
	 * <p>创建者：lxx</p>
	 */
	public  int  addBatchAssessDaiWork(List<AssessDaiWork> list)  throws  Exception;
}
