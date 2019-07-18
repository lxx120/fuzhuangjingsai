package com.match.reviewresult.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.match.reviewresult.model.Peizhi;

@Mapper
public interface PeizhiMapper {

	public int  addPeiZhi(Peizhi peizhi)  throws  Exception;
	
	public int  updatePeiZhi(Peizhi peizhi)  throws  Exception;
	
	@Select("select * from peizhi where type=#{type}")
	public Map<String,Object>  findPeiZhi(int type)  throws  Exception;
	
	@Select("select count(*) from peizhi where type=#{type}")
	int  countPeizhi(int type)  throws  Exception;
}
