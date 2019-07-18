package com.match.systemconfig.persist;

import java.io.Serializable;

/**
 * 系统配置 1.每月价格（ 按月收费n元/月 ） 2.每年价格（ 整年收费m元/年 ）
 * 			3.商品推广最大数
 */
public class SystemConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private double value;
	private String strValue;
	private String strornum;

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getStrornum() {
		return strornum;
	}

	public void setStrornum(String strornum) {
		this.strornum = strornum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}