package com.match.systemconfig.persist;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable  {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long enterprise;

	private String code;// 编号

	private String username;// 登录账户

	private String password;// 密码

	private boolean status;// 状态

	private String phone;

	private String name;

	private long roleId;

	private Date creatTime;// 创建时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public long getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(long enterprise) {
		this.enterprise = enterprise;
	}
	
}
