package com.match.mall.persist;

import java.io.Serializable;

public class Goods implements Serializable {

	/**
	 * 商品
	 */

	private static final long serialVersionUID = 1L;

	private long id;//

	private String name;// 商品名称

	private long goodstype;// 商品分类id

	private String guige;// 规格

	private String unit;// 单位

	private String pinyin;// 拼音

	private String pyjc;// 拼音简拼

	private double marketvalue;

	private String area;// 生产地

	private String enterprise;// 所属企业

	private String extra; // 附加属性
	
	private double price;
	
	private int recommend;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

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

	public long getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(long goodstype) {
		this.goodstype = goodstype;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPyjc() {
		return pyjc;
	}

	public void setPyjc(String pyjc) {
		this.pyjc = pyjc;
	}


	public double getMarketvalue() {
		return marketvalue;
	}

	public void setMarketvalue(double marketvalue) {
		this.marketvalue = marketvalue;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
