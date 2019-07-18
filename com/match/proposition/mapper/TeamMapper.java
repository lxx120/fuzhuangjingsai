package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Team;

@Mapper
public interface TeamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchTeam</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月10日 上午11:01:13</p>  
     * <p>创建者：lxx</p>
     */
    int addBatchTeam(List<Map<String,Object>> list) throws Exception;
    
    /**
     * 
     * <p>功能描述：通过报名id删除团队</p>
     * <p>方法名：deleteTeamByWorkid</p>
     * <p>@param signupid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年4月10日 下午3:54:18</p>  
     * <p>创建者：lxx</p>
     */
    int deleteTeamByWorkid(long signupid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询队伍详情</p>
     * <p>方法名：findTeamInfo</p>
     * <p>@param workid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月10日 下午4:13:11</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select name,groupleader,divisionwork,phone,major,grade from team where workid=#{workid}")
    public  List<Map<String,Object>>  findTeamInfo(long workid)  throws  Exception;
}