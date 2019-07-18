package com.match.systemconfig.persist;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户登录记录
 * 
 * @author admin
 *
 */
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long userid;// 用户id
	private String username;// 用户名
	private long roleid;// 角色
	private long merchant;// 所属商户
	private String merchantName;
	private Date logintime;// 登录时间
	private Date logouttime;// 登出时间
	private String loginfrom;// 1.pc,app,weixin
	private String loginip;// 登录ip
	private int valid;// 是否有效(1是 2 否)
	private String token;// 用户token
    private String photo;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public long getMerchant() {
		return merchant;
	}

	public void setMerchant(long merchant) {
		this.merchant = merchant;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public Date getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Date logouttime) {
		this.logouttime = logouttime;
	}

	public String getLoginfrom() {
		return loginfrom;
	}

	public void setLoginfrom(String loginfrom) {
		this.loginfrom = loginfrom;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}