package com.match.proposition.model;

import java.util.Date;

public class Competition {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String name;

    private Date stime;

    private Date etime;

    private String abstraction;

    private String img;

    private Integer status;

    private String note;
    
    private Date  themeetime;
    
    private Date  themestime;
    
    private Date  commitstime;
    
    private Date  commitetime;
    
    private Date  intermatchstime;
    
    private Date  intermatchetime;
    
    

    public Date getIntermatchstime() {
		return intermatchstime;
	}

	public void setIntermatchstime(Date intermatchstime) {
		this.intermatchstime = intermatchstime;
	}

	public Date getIntermatchetime() {
		return intermatchetime;
	}

	public void setIntermatchetime(Date intermatchetime) {
		this.intermatchetime = intermatchetime;
	}

	public Date getThemeetime() {
		return themeetime;
	}

	public void setThemeetime(Date themeetime) {
		this.themeetime = themeetime;
	}

	public Date getThemestime() {
		return themestime;
	}

	public void setThemestime(Date themestime) {
		this.themestime = themestime;
	}

	public Date getCommitstime() {
		return commitstime;
	}

	public void setCommitstime(Date commitstime) {
		this.commitstime = commitstime;
	}

	public Date getCommitetime() {
		return commitetime;
	}

	public void setCommitetime(Date commitetime) {
		this.commitetime = commitetime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getAbstraction() {
        return abstraction;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction == null ? null : abstraction.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}