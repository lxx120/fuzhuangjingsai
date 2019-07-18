package com.match.judges.model;

import java.util.Date;

public class AssessHierarchy {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String ahname;

    private String ahcode;

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

    public String getAhname() {
        return ahname;
    }

    public void setAhname(String ahname) {
        this.ahname = ahname == null ? null : ahname.trim();
    }

    public String getAhcode() {
        return ahcode;
    }

    public void setAhcode(String ahcode) {
        this.ahcode = ahcode == null ? null : ahcode.trim();
    }
}