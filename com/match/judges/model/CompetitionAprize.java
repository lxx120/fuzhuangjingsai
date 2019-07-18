package com.match.judges.model;

import java.util.Date;

public class CompetitionAprize {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long competitionid;

    private Long divisionid;

    private Long arid;

    private String name;

    private Integer minpercent;

    private Integer maxpercent;

    private Integer score;

    private Integer valid;
    
    private Integer  catype;
    
    private  String  comprize;
    
    
    

    public String getComprize() {
		return comprize;
	}

	public void setComprize(String comprize) {
		this.comprize = comprize;
	}

	public Integer getCatype() {
		return catype;
	}

	public void setCatype(Integer catype) {
		this.catype = catype;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMinpercent() {
		return minpercent;
	}

	public void setMinpercent(Integer minpercent) {
		this.minpercent = minpercent;
	}

	public Integer getMaxpercent() {
		return maxpercent;
	}

	public void setMaxpercent(Integer maxpercent) {
		this.maxpercent = maxpercent;
	}

	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}