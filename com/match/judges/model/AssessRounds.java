package com.match.judges.model;

import java.util.Date;

public class AssessRounds {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long ahid;

    private Integer type;

    private Long divisionid;

    private Long competitionid;

    private Long pre;

    private Integer artype;
    
    private  String  reviewstime;
    
    private  String  reviewetime;
    
    private  Integer isSchool;
    
    private  Integer  ischoose;
    
    private  Integer  level;
    
    //状态  -1 关闭  0 暂未开始  1 正在进行 
    private  Integer  status;
    
    private  Integer  generalcomment;
    
    private  String  mark;
    
    
    

    public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Integer getGeneralcomment() {
		return generalcomment;
	}

	public void setGeneralcomment(Integer generalcomment) {
		this.generalcomment = generalcomment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getReviewstime() {
		return reviewstime;
	}

	public void setReviewstime(String reviewstime) {
		this.reviewstime = reviewstime;
	}

	public String getReviewetime() {
		return reviewetime;
	}

	public void setReviewetime(String reviewetime) {
		this.reviewetime = reviewetime;
	}

	public Integer getIsSchool() {
		return isSchool;
	}

	public void setIsSchool(Integer isSchool) {
		this.isSchool = isSchool;
	}

	public Integer getIschoose() {
		return ischoose;
	}

	public void setIschoose(Integer ischoose) {
		this.ischoose = ischoose;
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

    public Long getAhid() {
        return ahid;
    }

    public void setAhid(Long ahid) {
        this.ahid = ahid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Long divisionid) {
        this.divisionid = divisionid;
    }

    public Long getCompetitionid() {
        return competitionid;
    }

    public void setCompetitionid(Long competitionid) {
        this.competitionid = competitionid;
    }

    public Long getPre() {
        return pre;
    }

    public void setPre(Long pre) {
        this.pre = pre;
    }

    public Integer getArtype() {
        return artype;
    }

    public void setArtype(Integer artype) {
        this.artype = artype;
    }
}