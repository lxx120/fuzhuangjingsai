package com.match.baciss.model;

import java.util.Date;

public class LoginMessage {

	
	private  long  id;//用户id
	private  long  divisionid;//所在赛区id
	private  String  username;  //登录名
	private  String  realname;  //真实姓名
//	private Date logintime;// 登录时间
//	private Date logouttime;// 登出时间
	private Integer status;//比赛状态
	private String comname; //比赛名称
	private long enterpriseid; //企业id
	private long competitionid; //竞赛id
	private Integer usertype;  //用户类型
	private Integer isperfect; //是否完善资质
	private Integer manager; //是否为管理者
	private Integer isjudge;//是否为评委
	private long  judgeid;//评委id
	private Integer  leader; //是否为领导
	private String phone;//手机号
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getLeader() {
		return leader;
	}
	public void setLeader(Integer leader) {
		this.leader = leader;
	}
	public Integer getIsjudge() {
		return isjudge;
	}
	public void setIsjudge(Integer isjudge) {
		this.isjudge = isjudge;
	}
	public long getJudgeid() {
		return judgeid;
	}
	public void setJudgeid(long judgeid) {
		this.judgeid = judgeid;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Integer getIsperfect() {
		return isperfect;
	}
	public void setIsperfect(Integer isperfect) {
		this.isperfect = isperfect;
	}
	public long getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(long enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public long getCompetitionid() {
		return competitionid;
	}
	public void setCompetitionid(long competitionid) {
		this.competitionid = competitionid;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDivisionid() {
		return divisionid;
	}
	public void setDivisionid(long divisionid) {
		this.divisionid = divisionid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
//	public Date getLogintime() {
//		return logintime;
//	}
//	public void setLogintime(Date logintime) {
//		this.logintime = logintime;
//	}
//	public Date getLogouttime() {
//		return logouttime;
//	}
//	public void setLogouttime(Date logouttime) {
//		this.logouttime = logouttime;
//	}
	
	
}
