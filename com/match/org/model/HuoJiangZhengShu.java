package com.match.org.model;

public class HuoJiangZhengShu {

	private  String  collegename;  //学校名称
	
	private  String  stwcode;  //作品编号
	
	private  String  prizename; //获奖名称
	
	private  String  realName;  //作者姓名
	
	private  String  teacherename;  //老师姓名
	
	private  String  title;//作品名称
	
	private  long  competitionid;
	
	

	public long getCompetitionid() {
		return competitionid;
	}

	public void setCompetitionid(long competitionid) {
		this.competitionid = competitionid;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getStwcode() {
		return stwcode;
	}

	public void setStwcode(String stwcode) {
		this.stwcode = stwcode;
	}

	public String getPrizename() {
		return prizename;
	}

	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTeacherename() {
		return teacherename;
	}

	public void setTeacherename(String teacherename) {
		this.teacherename = teacherename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
