package com.match.proposition.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ThemeSignup {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long userid;

    private Date signuptime;

    private Long themeid;

    private Integer postworks;

    private String teacher;

    private Integer teachauthen;

    private Date teacherauthentime;

    private Long competitionid;

    private Long divisionid;

    private String workType;

    private Integer isupload;

    private String picspath;
    
    private Integer  competitionType; //参赛类型  1 个人  2团体
    
    private String teacherphone;
    
    private String abpicspath;
    
    private String realname;
    
    private String phone;
    
    private  String  stwcode;
    
    private  String  stitle;
    
    
    
    public String getStwcode() {
		return stwcode;
	}

	public void setStwcode(String stwcode) {
		this.stwcode = stwcode;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAbpicspath() {
		return abpicspath;
	}

	public void setAbpicspath(String abpicspath) {
		this.abpicspath = abpicspath;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTeacherphone() {
		return teacherphone;
	}

	public void setTeacherphone(String teacherphone) {
		this.teacherphone = teacherphone;
	}

	private  List<Map<String,Object>>  list;

    
    
    public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Integer getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(Integer competitionType) {
		this.competitionType = competitionType;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getSignuptime() {
        return signuptime;
    }

    public void setSignuptime(Date signuptime) {
        this.signuptime = signuptime;
    }

    public Long getThemeid() {
        return themeid;
    }

    public void setThemeid(Long themeid) {
        this.themeid = themeid;
    }

    public Integer getPostworks() {
        return postworks;
    }

    public void setPostworks(Integer postworks) {
        this.postworks = postworks;
    }

    public Integer getTeachauthen() {
        return teachauthen;
    }

    public void setTeachauthen(Integer teachauthen) {
        this.teachauthen = teachauthen;
    }

    public Date getTeacherauthentime() {
        return teacherauthentime;
    }

    public void setTeacherauthentime(Date teacherauthentime) {
        this.teacherauthentime = teacherauthentime;
    }

    public Long getCompetitionid() {
        return competitionid;
    }

    public void setCompetitionid(Long competitionid) {
        this.competitionid = competitionid;
    }

    public Long getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Long divisionid) {
        this.divisionid = divisionid;
    }

    

    public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getIsupload() {
        return isupload;
    }

    public void setIsupload(Integer isupload) {
        this.isupload = isupload;
    }

    public String getPicspath() {
        return picspath;
    }

    public void setPicspath(String picspath) {
        this.picspath = picspath == null ? null : picspath.trim();
    }
}