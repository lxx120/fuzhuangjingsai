package com.match.proposition.model;

import java.util.Date;

public class CheatReport {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long themeworkid;

    private Long reporterid;

    private String fromip;

    private Boolean checked;

    private Long checkorid;

    private Integer crtype;

    private String website;

    private Integer syscheck;

    private String content;

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

    public Long getThemeworkid() {
        return themeworkid;
    }

    public void setThemeworkid(Long themeworkid) {
        this.themeworkid = themeworkid;
    }

    public Long getReporterid() {
        return reporterid;
    }

    public void setReporterid(Long reporterid) {
        this.reporterid = reporterid;
    }

    public String getFromip() {
        return fromip;
    }

    public void setFromip(String fromip) {
        this.fromip = fromip == null ? null : fromip.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getCheckorid() {
        return checkorid;
    }

    public void setCheckorid(Long checkorid) {
        this.checkorid = checkorid;
    }

    public Integer getCrtype() {
        return crtype;
    }

    public void setCrtype(Integer crtype) {
        this.crtype = crtype;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public Integer getSyscheck() {
        return syscheck;
    }

    public void setSyscheck(Integer syscheck) {
        this.syscheck = syscheck;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}