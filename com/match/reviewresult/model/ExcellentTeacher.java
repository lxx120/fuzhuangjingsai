package com.match.reviewresult.model;

import java.util.Date;

public class ExcellentTeacher {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long competationid;

    private Long divisionid;

    private Integer totalcsore;

    private Integer ranking;

    private Long teacherid;
    
    private  int  type;
    
    private  String  phone;
    

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

    public Long getCompetationid() {
        return competationid;
    }

    public void setCompetationid(Long competationid) {
        this.competationid = competationid;
    }

    public Long getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Long divisionid) {
        this.divisionid = divisionid;
    }

    

    public Integer getTotalcsore() {
		return totalcsore;
	}

	public void setTotalcsore(Integer totalcsore) {
		this.totalcsore = totalcsore;
	}

	public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }
}