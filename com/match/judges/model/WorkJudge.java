package com.match.judges.model;

import java.util.Date;

public class WorkJudge {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long division;

    private Long worksid;

    private Long judgeid;

    private Integer judged;

    private Double score;

    private Date judgetime;

    private Long arid;

    private Integer isvote;
    
    private Long  groupid;
    
    private  Long  competitionid;
    
    private  Double  weight;
    
    
    

    public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Long getCompetitionid() {
		return competitionid;
	}

	public void setCompetitionid(Long competitionid) {
		this.competitionid = competitionid;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
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

    public Long getDivision() {
        return division;
    }

    public void setDivision(Long division) {
        this.division = division;
    }

    public Long getWorksid() {
        return worksid;
    }

    public void setWorksid(Long worksid) {
        this.worksid = worksid;
    }

    public Long getJudgeid() {
        return judgeid;
    }

    public void setJudgeid(Long judgeid) {
        this.judgeid = judgeid;
    }

    public Integer getJudged() {
        return judged;
    }

    public void setJudged(Integer judged) {
        this.judged = judged;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getJudgetime() {
        return judgetime;
    }

    public void setJudgetime(Date judgetime) {
        this.judgetime = judgetime;
    }

    public Long getArid() {
        return arid;
    }

    public void setArid(Long arid) {
        this.arid = arid;
    }

    public Integer getIsvote() {
        return isvote;
    }

    public void setIsvote(Integer isvote) {
        this.isvote = isvote;
    }
}