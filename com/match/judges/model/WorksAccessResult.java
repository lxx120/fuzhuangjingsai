package com.match.judges.model;

import java.util.Date;

public class WorksAccessResult {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long workid;

    private Long competitionid;

    private Long divisionid;

    private Long arid;

    private Double totalscore;

    private Double totalticket;

    private Integer wartype;

    private Integer ranking;
    
    private String wtypecode;
    
    

    public String getWtypecode() {
		return wtypecode;
	}

	public void setWtypecode(String wtypecode) {
		this.wtypecode = wtypecode;
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

    public Double getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Double totalscore) {
        this.totalscore = totalscore;
    }

  

    public Double getTotalticket() {
		return totalticket;
	}

	public void setTotalticket(Double totalticket) {
		this.totalticket = totalticket;
	}

	public Integer getWartype() {
        return wartype;
    }

    public void setWartype(Integer wartype) {
        this.wartype = wartype;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}