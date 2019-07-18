package com.match.mall.persist;

import java.io.Serializable;

/**
 * 购物车
 *
 * @author admin
 *
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long userid; //用户id
	private long goodsid;// 商户商品id
	private String unit;// 单位
	private int number;// 数量
	private double price;// 价格
	private String guige;
	private String goodsSKU;
	private double money;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(long goodsid) {
		this.goodsid = goodsid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getGoodsSKU() {
		return goodsSKU;
	}
	public void setGoodsSKU(String goodsSKU) {
		this.goodsSKU = goodsSKU;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}