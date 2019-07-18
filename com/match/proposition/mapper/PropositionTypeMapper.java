package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.PropositionType;

@Mapper
public interface PropositionTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropositionType record);

    int insertSelective(PropositionType record);

    PropositionType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PropositionType record);

    int updateByPrimaryKey(PropositionType record);
    
    /**
     * 
     * <p>功能描述：查询命题类型</p>
     * <p>方法名：findPropositionType</p>
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月11日 上午10:03:20</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select code,name from PropositionType order by ctime")
    public  List<Map<String,Object>>  findPropositionType()  throws  Exception;
}