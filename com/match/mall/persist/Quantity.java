package com.match.mall.persist;

import java.io.Serializable;

public class Quantity implements Serializable {
	
	/**
	 * 特征量
	 */
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;// 名字
	
	private int sale;// 是否销售属性（是否参与sku组合）
	
	private int keyed;// 是否必须属性
	
	private int state;// 启用状态（1启用 2 禁用）
	
	private int indx;// 顺序
	
	private long goodstype;// 商品分类id
	
	private int enumed;// 是否多选
	
	private int inputed;// 是否可输入
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public int getKeyed() {
		return keyed;
	}

	public void setKeyed(int keyed) {
		this.keyed = keyed;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIndx() {
		return indx;
	}

	public void setIndx(int indx) {
		this.indx = indx;
	}

	public long getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(long goodstype) {
		this.goodstype = goodstype;
	}

	public int getEnumed() {
		return enumed;
	}

	public void setEnumed(int enumed) {
		this.enumed = enumed;
	}

	public int getInputed() {
		return inputed;
	}

	public void setInputed(int inputed) {
		this.inputed = inputed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
