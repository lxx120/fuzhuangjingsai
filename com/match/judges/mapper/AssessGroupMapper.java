package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.AssessGroup;

@Mapper
public interface AssessGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssessGroup record);

    int insertSelective(AssessGroup record);

    AssessGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssessGroup record);

    int updateByPrimaryKey(AssessGroup record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：insetList</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年3月29日 下午6:00:58</p>  
     * <p>创建者：lxx</p>
     */
    int insetList(List<Map<String,Object>>  list)  throws Exception;
    
    @Select("select id,name,arrid from AssessGroup where arrid=#{arrid} order by ctime")
    List<Map<String,Object>>  findAssessGroupList(long arrid)  throws  Exception;  
    
    @Select("SELECT c.mark,d.ahcode,a.`level`,c.`pre`,c.`id` AS ahid,c.divisionid,c.competitionid,b.`groupnum`,b.`sumworks`,b.`sumteacher` FROM assessgroup a LEFT JOIN assessrole b ON a.`arrid`=b.`id` left join assessrounds c on b.arid=c.id left join assesshierarchy d on c.ahid=d.id where a.id=#{id}")
	public Map<String, Object> findinfoByGroupId(long id) throws Exception;

    
}