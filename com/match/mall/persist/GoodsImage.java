package com.match.mall.persist;

import java.io.Serializable;

/**
 * 商品图片
 * 
 */
public class GoodsImage implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long goodsid;// goodsid
	private String url;// 图片
	private String type;// 类型

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
