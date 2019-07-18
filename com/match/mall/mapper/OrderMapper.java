package com.match.mall.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.match.mall.persist.Order;

public interface OrderMapper {

	int count(@Param("code")String code,@Param("membername")String membername,@Param("begin")Date begin,@Param("end")Date end,@Param("cargo")int cargo);

	List<Map<String, Object>> getorderList(@Param("code")String code,@Param("membername") String membername,@Param("begin") Date begin, @Param("end")Date end, @Param("cargo")int cargo, @Param("page")int page,
			@Param("pagesize")int pagesize);

	List<Map<String, Object>> getorderDetail(@Param("orderid")long orderid);

	void sendOut(@Param("orderid")long orderid);

	

}
