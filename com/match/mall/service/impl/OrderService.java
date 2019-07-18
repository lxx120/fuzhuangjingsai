package com.match.mall.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.match.common.result.ObjectResult;
import com.match.common.result.PageRequest;
import com.match.common.result.PageResult;
import com.match.dao.common.Daomethod;
import com.match.mall.mapper.MemberMapper;
import com.match.mall.mapper.OrderMapper;
import com.match.mall.persist.Member;
import com.match.mall.persist.Order;
import com.match.mall.service.IOrderService;
@Service
public class OrderService implements IOrderService {

	@Resource
	private OrderMapper orderper;
	
	@Override
	@Transactional(rollbackOn=Exception.class)
	public PageResult<Map<String, Object>> getorderList(Order order,Date begin, Date end,PageRequest pr) {
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		if(pr==null){
			pr=new PageRequest(0,30,null);
			pr.setNeedPages(true);
		}
		if(pr.isNeedPages()){
			int count=orderper.count(order.getCode(),order.getMembername(),begin,end,order.getCargo());
			prr.setPages(Daomethod.countpages(count, pr.getPageSize()));
			prr.setRowCount(count);
		}
		try{
			List<Map<String,Object>> ol = orderper.getorderList(order.getCode(),order.getMembername(),begin,end,order.getCargo(), pr.getStart(), pr.getPageSize());
			prr.setCode(0);
			prr.setItems(ol);
		}catch(Exception e){
			e.printStackTrace();
		}
		return prr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public PageResult<Map<String,Object>> getDetail(long orderid) {
		PageResult<Map<String,Object>> prr=new PageResult<Map<String,Object>>();
		if(orderid<=0) {
			prr.setCode(1);
			prr.setErrmsg("id为空");
			return prr;
		}
		try{
			List<Map<String,Object>> od = orderper.getorderDetail(orderid);
			prr.setCode(0);
			prr.setItems(od);
			prr.setErrmsg("查询成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return prr;
	}

	@Override
	@Transactional(rollbackOn=Exception.class)
	public ObjectResult<Map<String, Object>> sendOut(long orderid) {
		ObjectResult<Map<String,Object>> orr=new ObjectResult<Map<String,Object>>();
		if(orderid<=0){
			orr.setCode(1);
			orr.setErrmsg("id为空");
			return orr;
		}
		try{
			orderper.sendOut(orderid);
			orr.setCode(0);
			orr.setErrmsg("发货成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return orr;
	}

}
