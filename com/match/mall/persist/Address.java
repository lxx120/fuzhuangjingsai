package com.match.mall.persist;

import java.io.Serializable;

/**
 * 收货地址
 *
 * @author admin
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private long userid; //用户id
	private String receiveperson; //收货人
	private String province;
	private String city;
	private String area;
	private String detail;
	private String phone;
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
	public String getReceiveperson() {
		return receiveperson;
	}
	public void setReceiveperson(String receiveperson) {
		this.receiveperson = receiveperson;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
