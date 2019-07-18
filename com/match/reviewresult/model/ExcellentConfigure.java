package com.match.reviewresult.model;

import java.util.Date;

public class ExcellentConfigure {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long competationid;

    private Long divisionid;

    private Integer excellentperson;

    private Integer excellentteacher;

    private Integer excellentcolldge;

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

    public Long getCompetationid() {
        return competationid;
    }

    public void setCompetationid(Long competationid) {
        this.competationid = competationid;
    }

    public Long getDivisionid() {
        return divisionid;
    }

    public void setDivisionid(Long divisionid) {
        this.divisionid = divisionid;
    }

    public Integer getExcellentperson() {
        return excellentperson;
    }

    public void setExcellentperson(Integer excellentperson) {
        this.excellentperson = excellentperson;
    }

    public Integer getExcellentteacher() {
        return excellentteacher;
    }

    public void setExcellentteacher(Integer excellentteacher) {
        this.excellentteacher = excellentteacher;
    }

    public Integer getExcellentcolldge() {
        return excellentcolldge;
    }

    public void setExcellentcolldge(Integer excellentcolldge) {
        this.excellentcolldge = excellentcolldge;
    }
}