package com.match.proposition.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ThemeWork {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private Long themeid;

    private Long enterpriseid;

    private Long competitionid;

    private Long divisionid;

    private Long collegeid;

    private Long teacherid;

    private Boolean teacherchecked;

    private String title;

    private String abstraction;

    private Integer views;

    private Long recommendsid;

    private Double score;

    private Boolean judged;

    private Integer cheat;

    private Boolean publicity;

    private String prize;

    private String worktypecode;

    private Boolean isteam;

    private String propositiontypecode;

    private Long eliminatelevelid;

    private Boolean ispromotion;

    private Long finalaprizeid;

    private String twcode;

    private String content;
    
    private  List<Map<String,Object>>  list;
    
    private  Long  themesignupid;
    
    private  String  tabpicspath;
    
    
    

    public String getTabpicspath() {
		return tabpicspath;
	}

	public void setTabpicspath(String tabpicspath) {
		this.tabpicspath = tabpicspath;
	}

	public Long getThemesignupid() {
		return themesignupid;
	}

	public void setThemesignupid(Long themesignupid) {
		this.themesignupid = themesignupid;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
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

    public Long getThemeid() {
        return themeid;
    }

    public void setThemeid(Long themeid) {
        this.themeid = themeid;
    }

    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
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

    public Long getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(Long collegeid) {
        this.collegeid = collegeid;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public Boolean getTeacherchecked() {
        return teacherchecked;
    }

    public void setTeacherchecked(Boolean teacherchecked) {
        this.teacherchecked = teacherchecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAbstraction() {
        return abstraction;
    }

    public void setAbstraction(String abstraction) {
        this.abstraction = abstraction == null ? null : abstraction.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getRecommendsid() {
        return recommendsid;
    }

    public void setRecommendsid(Long recommendsid) {
        this.recommendsid = recommendsid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getJudged() {
        return judged;
    }

    public void setJudged(Boolean judged) {
        this.judged = judged;
    }

    public Integer getCheat() {
        return cheat;
    }

    public void setCheat(Integer cheat) {
        this.cheat = cheat;
    }

    public Boolean getPublicity() {
        return publicity;
    }

    public void setPublicity(Boolean publicity) {
        this.publicity = publicity;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize == null ? null : prize.trim();
    }

    public String getWorktypecode() {
        return worktypecode;
    }

    public void setWorktypecode(String worktypecode) {
        this.worktypecode = worktypecode == null ? null : worktypecode.trim();
    }

    public Boolean getIsteam() {
        return isteam;
    }

    public void setIsteam(Boolean isteam) {
        this.isteam = isteam;
    }

    public String getPropositiontypecode() {
        return propositiontypecode;
    }

    public void setPropositiontypecode(String propositiontypecode) {
        this.propositiontypecode = propositiontypecode == null ? null : propositiontypecode.trim();
    }

    public Long getEliminatelevelid() {
        return eliminatelevelid;
    }

    public void setEliminatelevelid(Long eliminatelevelid) {
        this.eliminatelevelid = eliminatelevelid;
    }

    public Boolean getIspromotion() {
        return ispromotion;
    }

    public void setIspromotion(Boolean ispromotion) {
        this.ispromotion = ispromotion;
    }

    public Long getFinalaprizeid() {
        return finalaprizeid;
    }

    public void setFinalaprizeid(Long finalaprizeid) {
        this.finalaprizeid = finalaprizeid;
    }

    public String getTwcode() {
        return twcode;
    }

    public void setTwcode(String twcode) {
        this.twcode = twcode == null ? null : twcode.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}