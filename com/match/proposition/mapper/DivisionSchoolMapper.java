package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.DivisionSchool;

@Mapper
public interface DivisionSchoolMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DivisionSchool record);

    int insertSelective(DivisionSchool record);

    DivisionSchool selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DivisionSchool record);

    int updateByPrimaryKey(DivisionSchool record);
    
    /**
     * 
     * <p>功能描述：赛区负责人查询未绑定的学校</p>
     * <p>方法名：findOthersCollegePage</p>
     * <p>@param competitionid
     * <p>@param divisionid
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月1日 下午4:26:37</p>  
     * <p>创建者：lxx</p>
     */
	public  List<Map<String,Object>>  findOthersCollegePage(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;
	
	int countfindOthersCollegePage(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询赛区绑定的学校</p>
	 * <p>方法名：findBangDingCollege</p>
	 * <p>@param province
	 * <p>@param city
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月1日 下午6:02:03</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findBangDingCollege(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name,@Param("page") int page,@Param("pagesize") int pagesize)  throws Exception;
	
	int countBangDingCollege(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：批量添加学校赛区</p>
	 * <p>方法名：addBatchDivisionSchool</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年4月1日 下午6:31:56</p>  
	 * <p>创建者：lxx</p>
	 */
	int addBatchDivisionSchool(List<Map<String,Object>> list)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：通过赛区id查询所绑定的学校</p>
	 * <p>方法名：findSchoolByDivisionID</p>
	 * <p>@param divisionid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年4月25日 下午2:51:09</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select schoolid from divisionschool where divisionid=#{divisionid}")
	List<Map<String,Object>>  findSchoolByDivisionID(long divisionid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询这个赛区是否绑定过</p>
	 * <p>方法名：findExist</p>
	 * <p>@param divisionid
	 * <p>@param schoolid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月30日 下午5:13:42</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from divisionschool where divisionid=#{divisionid} and schoolid=#{schoolid}")
	public  Map<String,Object>  findExist(@Param("divisionid") long divisionid,@Param("schoolid") long schoolid)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：查询这个学校是否被其他赛区绑定过</p>
	 * <p>方法名：findExistOtherDivision</p>
	 * <p>@param competitionid
	 * <p>@param schoolid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月30日 下午5:15:05</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from divisionschool where competitionid=#{competitionid} and schoolid=#{schoolid}")
	public  Map<String,Object>  findExistOtherDivision(@Param("competitionid") long competitionid,@Param("schoolid") long schoolid)  throws  Exception;
}