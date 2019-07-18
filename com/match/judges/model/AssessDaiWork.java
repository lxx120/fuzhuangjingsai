package com.match.judges.model;

import java.util.Date;

public class AssessDaiWork {

	private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long arid;
    
    private Long workid;
    
    private  Long  competitionid;
    
    private  Long  divisionid;
    
    

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

	public Long getArid() {
		return arid;
	}

	public void setArid(Long arid) {
		this.arid = arid;
	}

	public Long getWorkid() {
		return workid;
	}

	public void setWorkid(Long workid) {
		this.workid = workid;
	}
    
    
}
