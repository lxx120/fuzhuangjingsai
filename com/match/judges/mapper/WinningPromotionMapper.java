package com.match.judges.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.judges.model.WinningPromotion;

public interface WinningPromotionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WinningPromotion record);

    int insertSelective(WinningPromotion record);

    WinningPromotion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WinningPromotion record);

    int updateByPrimaryKey(WinningPromotion record);
    
    /**
     * 
     * <p>功能描述：批量添加</p>
     * <p>方法名：addBatchWin</p>
     * <p>@param list
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：int</p>
     * <p>创建日期：2019年5月6日 下午3:19:21</p>  
     * <p>创建者：lxx</p>
     */
    public  int  addBatchWin(List<WinningPromotion>  list)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：查询该晋级的作品</p>
     * <p>方法名：findWorkList</p>
     * <p>@param mark
     * <p>@param divisionid
     * <p>@param competitionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月7日 下午4:37:29</p>  
     * <p>创建者：lxx</p>
     */
    public  List<Map<String,Object>> findWorkList(@Param("mark") String mark,@Param("divisionid") long divisionid,@Param("competitionid") long competitionid)  throws  Exception;
    
    
    public  List<Map<String,Object>> findWorkList1(@Param("competitionid") long competitionid)  throws  Exception;
    
    public  List<Map<String,Object>>  findWorklIst2(long aird)  throws  Exception;
    
    public  List<Map<String,Object>> findHuoJiangThemeWork(@Param("code") String code,@Param("divisionid") long divisionid,@Param("competitionid") long competitionid)  throws Exception;
}