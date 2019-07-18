package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.Division;

@Mapper
public interface DivisionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Division record);

    int insertSelective(Division record);

    Division selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Division record);

    int updateByPrimaryKeyWithBLOBs(Division record);

    int updateByPrimaryKey(Division record);
    
    public int  insetList(List<Map<String,Object>>  list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：赛区详情（单个）</p>
     * <p>方法名：findDivisionById</p>
     * <p>@param id
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：Map<String,Object></p>
     * <p>创建日期：2019年4月18日 下午6:37:00</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select * from division where id=#{id}")
    public Map<String,Object>  findDivisionById(long id)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：分页</p>
     * <p>方法名：findDivisionPage</p>
     * <p>@param name
     * <p>@param comid
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月18日 下午6:43:59</p>  
     * <p>创建者：lxx</p>
     */
    public List<Map<String,Object>>  findDivisionPage(@Param("name") String name,@Param("comid")  String comid,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
    
    public  int  countfindDivisionPage(@Param("name") String name,@Param("comid")  String comid)  throws  Exception;

}