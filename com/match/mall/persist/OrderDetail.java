package com.match.mall.persist;

import java.util.Date;

public class OrderDetail {

	/**
	 * 订单详情
	 */
	private static final long serialVersionUID = 5231134212346077681L;
	
	private long id;        //id
	
	private long orderId;   //订单id
	
	private long goodsId;   //商品id
	
	private int num;        //数量

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
