package com.match.mall.persist;

import java.io.Serializable;

public class GoodsValue implements Serializable {
	
	/**
	 * 特征值
	 */
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long goodsid;
	
	private long quantityid;// 特征量id
	
	private String quantity;// 特征量
	
	private String value;// 特征值
	
	private int enumed;// 是否多选

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(long goodsid) {
		this.goodsid = goodsid;
	}

	public long getQuantityid() {
		return quantityid;
	}

	public void setQuantityid(long quantityid) {
		this.quantityid = quantityid;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getEnumed() {
		return enumed;
	}

	public void setEnumed(int enumed) {
		this.enumed = enumed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
