package com.match.judges.model;

import java.util.Date;

public class AssessRole {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String worktypecode;

    private Integer sumworks;

    private Integer groupnum;

    private Integer sumteacher;

    private Long arid;
    
    private  Long competitionid;
    
    private  Long devisionid;
    
    

    public Long getCompetitionid() {
		return competitionid;
	}

	public void setCompetitionid(Long competitionid) {
		this.competitionid = competitionid;
	}

	public Long getDevisionid() {
		return devisionid;
	}

	public void setDevisionid(Long devisionid) {
		this.devisionid = devisionid;
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

    public String getWorktypecode() {
        return worktypecode;
    }

    public void setWorktypecode(String worktypecode) {
        this.worktypecode = worktypecode == null ? null : worktypecode.trim();
    }

    public Integer getSumworks() {
        return sumworks;
    }

    public void setSumworks(Integer sumworks) {
        this.sumworks = sumworks;
    }

    public Integer getGroupnum() {
        return groupnum;
    }

    public void setGroupnum(Integer groupnum) {
        this.groupnum = groupnum;
    }

    public Integer getSumteacher() {
        return sumteacher;
    }

    public void setSumteacher(Integer sumteacher) {
        this.sumteacher = sumteacher;
    }

    public Long getArid() {
        return arid;
    }

    public void setArid(Long arid) {
        this.arid = arid;
    }
}