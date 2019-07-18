package com.match.judges.model;

import java.util.Date;

public class WinningPromotion {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long workid;

    private Long competitionid;

    private Long divisionid;

    private Long arid;

    private Boolean finalaprize;

    private Integer wptype;

    private Integer ranking;
    
    private String mark;
    
    private long competitionAprizeid;
    
    private  String  ptypecode;
    

    public String getPtypecode() {
		return ptypecode;
	}

	public void setPtypecode(String ptypecode) {
		this.ptypecode = ptypecode;
	}

	public long getCompetitionAprizeid() {
		return competitionAprizeid;
	}

	public void setCompetitionAprizeid(long competitionAprizeid) {
		this.competitionAprizeid = competitionAprizeid;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public Long getWorkid() {
        return workid;
    }

    public void setWorkid(Long workid) {
        this.workid = workid;
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

    public Long getArid() {
        return arid;
    }

    public void setArid(Long arid) {
        this.arid = arid;
    }


    public Boolean getFinalaprize() {
        return finalaprize;
    }

    public void setFinalaprize(Boolean finalaprize) {
        this.finalaprize = finalaprize;
    }

    public Integer getWptype() {
        return wptype;
    }

    public void setWptype(Integer wptype) {
        this.wptype = wptype;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}