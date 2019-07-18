package com.match.mall.service;

import java.util.Date;
import java.util.Map;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.mall.persist.Member;
import com.match.mall.persist.Order;

public interface IOrderService {

	//获取订单列表
	public PageResult<Map<String, Object>> getorderList(Order order, Date begin, Date end, PageRequest pr);

	//获取订单详情
	public PageResult<Map<String, Object>> getDetail(long orderid);

	//发货
	public ObjectResult<Map<String, Object>> sendOut(long orderid);
	
}
