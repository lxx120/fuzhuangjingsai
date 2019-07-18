package com.match.proposition.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.match.proposition.model.DivisionCompany;

@Mapper
public interface DivisionCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DivisionCompany record);

    int insertSelective(DivisionCompany record);

    DivisionCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DivisionCompany record);

    int updateByPrimaryKey(DivisionCompany record);
    
    /**
     * 
     * <p>功能描述：通过赛区id查询绑定的企业</p>
     * <p>方法名：findCompanyByDivisionID</p>
     * <p>@param divisionid
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年4月25日 下午2:53:14</p>  
     * <p>创建者：lxx</p>
     */
    @Select("select companyid from divisioncompany where divisionid=#{divisionid}")
	List<Map<String,Object>>  findCompanyByDivisionID(long divisionid)  throws  Exception;
    
    /**
     * 
     * <p>功能描述：赛区负责人-查询未绑定的企业</p>
     * <p>方法名：findOthersCompanyPage</p>
     * <p>@param competitionid
     * <p>@param province
     * <p>@param city
     * <p>@param divisionid
     * <p>@param name
     * <p>@param page
     * <p>@param pagesize
     * <p>@return
     * <p>@throws Exception</p>
     * <p>返回类型：List<Map<String,Object>></p>
     * <p>创建日期：2019年5月24日 下午3:09:48</p>  
     * <p>创建者：lxx</p>
     */
	public  List<Map<String,Object>>  findOthersCompanyPage(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name,@Param("page") int page,@Param("pagesize") int pagesize)  throws  Exception;

	int countfindOthersCompanyPage(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name)  throws  Exception;
	
	/**
	 * 
	 * <p>功能描述：赛区负责人-查询绑定的企业</p>
	 * <p>方法名：findBangDingCompany</p>
	 * <p>@param competitionid
	 * <p>@param province
	 * <p>@param city
	 * <p>@param divisionid
	 * <p>@param name
	 * <p>@param page
	 * <p>@param pagesize
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：List<Map<String,Object>></p>
	 * <p>创建日期：2019年5月24日 下午3:09:51</p>  
	 * <p>创建者：lxx</p>
	 */
	public  List<Map<String,Object>>  findBangDingCompany(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name,@Param("page") int page,@Param("pagesize") int pagesize)  throws Exception;

	int countBangDingCompany(@Param("competitionid") long competitionid,@Param("province") String province,@Param("city") String city,@Param("divisionid")long divisionid,@Param("name") String name)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：批量添加</p>
	 * <p>方法名：addBatchDivisionCompany</p>
	 * <p>@param list
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：int</p>
	 * <p>创建日期：2019年5月24日 下午3:09:55</p>  
	 * <p>创建者：lxx</p>
	 */
	int addBatchDivisionCompany(List<Map<String,Object>> list)  throws  Exception;

	/**
	 * 
	 * <p>功能描述：查询企业是否已经绑定过</p>
	 * <p>方法名：findExist</p>
	 * <p>@param divisionid
	 * <p>@param companyid
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：Map<String,Object></p>
	 * <p>创建日期：2019年5月30日 下午5:18:57</p>  
	 * <p>创建者：lxx</p>
	 */
	@Select("select id from divisioncompany where divisionid=#{divisionid} and companyid=#{companyid}")
	public  Map<String,Object>  findExist(@Param("divisionid") long divisionid,@Param("companyid") long companyid)  throws  Exception;
	
	@Select("select id from divisioncompany where competitionid=#{competitionid} and companyid=#{companyid}")
	public  Map<String,Object>  findExistOtherDivision(@Param("competitionid") long competitionid,@Param("companyid") long companyid)  throws  Exception;

}