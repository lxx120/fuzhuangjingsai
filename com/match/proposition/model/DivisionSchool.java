package com.match.proposition.model;

import java.util.Date;

public class DivisionSchool {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long divisionid;

    private Long schoolid;

    private Integer status;
    
    private Integer  type;
    
    private Integer promotionnum;
    
    private Long competitionid;
    
    

    public Long getCompetitionid() {
		return competitionid;
	}

	public void setCompetitionid(Long competitionid) {
		this.competitionid = competitionid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPromotionnum() {
		return promotionnum;
	}

	public void setPromotionnum(Integer promotionnum) {
		this.promotionnum = promotionnum;
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

    public Long getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Long divisionid) {
        this.divisionid = divisionid;
    }

    public Long getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Long schoolid) {
        this.schoolid = schoolid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}