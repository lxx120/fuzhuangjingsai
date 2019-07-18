package com.match.weixin.persist;

import java.io.Serializable;

public class Wechat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final long accessTokenExpire=7200;
	private static final long refreshTokenExpire=30*24*3600;
	
	private String openid;
	private String nickName;
	private long fromMerchant;
	private boolean state;//true:关注,false:不关注
	private long userid;//
	
	private String headimgurl;
	private String sex;
	private String country;
	private String province;
	private String city;
	
	
	private String access_token; //授权的accesstoken
	private long  tokenTimestamp;
	private String refresh_token;
	private long refreshTokenTimestamp;
	private String unionid;
		
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public long getFromMerchant() {
		return fromMerchant;
	}
	public void setFromMerchant(long fromMerchant) {
		this.fromMerchant = fromMerchant;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public long getTokenTimestamp() {
		return tokenTimestamp;
	}
	public void setTokenTimestamp(long tokenTimestamp) {
		this.tokenTimestamp = tokenTimestamp;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public long getRefreshTokenTimestamp() {
		return refreshTokenTimestamp;
	}
	public void setRefreshTokenTimestamp(long refreshTokenTimestamp) {
		this.refreshTokenTimestamp = refreshTokenTimestamp;
	}
	
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static long getRefreshtokenexpire() {
		return refreshTokenExpire;
	}
	public void firstAccessToken(String accessToken,String refreshToken){
		long time=System.currentTimeMillis();
		this.access_token=accessToken;
		this.refresh_token=refreshToken;
		this.tokenTimestamp=time;
		this.refreshTokenTimestamp=time;
	}
	
	public void refreshAccessToken(String accessToken){
		long time=System.currentTimeMillis();
		this.access_token=accessToken;
		this.tokenTimestamp=time;
	}
	
	public boolean accessTokenExpire(){
		long time=System.currentTimeMillis();
		return (time-this.tokenTimestamp)>Wechat.accessTokenExpire;
	}
	
	public boolean refreshAccessTokenExpire(){
		long time=System.currentTimeMillis();
		return (time-this.refreshTokenTimestamp)>Wechat.refreshTokenExpire;
	}
	
}
