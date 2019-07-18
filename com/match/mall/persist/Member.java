package com.match.mall.persist;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable{

	/**
	 * 商城会员
	 */
	private static final long serialVersionUID = 5231134212346077681L;
	
	private long id;        //id
	
	private String username;      //用户名
	
	private String password;  //密码
	
	private String phone;    //手机号
	
	private String mail;     //邮箱
	
	private String enterprise; //所属企业
	
	private String enterprisecode; //所属企业编号
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEnterprisecode() {
		return enterprisecode;
	}

	public void setEnterprisecode(String enterprisecode) {
		this.enterprisecode = enterprisecode;
	}
	
	
	

}
