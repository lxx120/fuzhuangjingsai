package com.match.mall.persist;

import java.io.Serializable;

public class GoodsSku implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long goodsid;
	
	private String salevalues;
	
	private double price;
	
	private double marketprice;

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

	public String getSalevalues() {
		return salevalues;
	}

	public void setSalevalues(String salevalues) {
		this.salevalues = salevalues;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}
	
}
