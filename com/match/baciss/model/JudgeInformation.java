package com.match.baciss.model;

import java.util.Date;

public class JudgeInformation {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String bankname;

    private String banknumber;

    private String bankaddress;

    private String resumeurl;

    private String skilledcheck;

    private String wantchecktheme;

    private String wantcheckworkstype;

    private Long userid;
    
    private  Long divisionid;
    
    private  Integer  levelAccess;
    
    private  String judgename;
    
    private  String judgephone;
    
    private  int  judgetype;  //1学校人员  2企业人员
    
    

   

    public int getJudgetype() {
		return judgetype;
	}

	public void setJudgetype(int judgetype) {
		this.judgetype = judgetype;
	}

	public String getJudgename() {
		return judgename;
	}

	public void setJudgename(String judgename) {
		this.judgename = judgename;
	}

	public String getJudgephone() {
		return judgephone;
	}

	public void setJudgephone(String judgephone) {
		this.judgephone = judgephone;
	}

	public Long getDivisionid() {
		return divisionid;
	}

	public void setDivisionid(Long divisionid) {
		this.divisionid = divisionid;
	}

	public Integer getLevelAccess() {
		return levelAccess;
	}

	public void setLevelAccess(Integer levelAccess) {
		this.levelAccess = levelAccess;
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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }


    public String getBanknumber() {
		return banknumber;
	}

	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}

	public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress == null ? null : bankaddress.trim();
    }

    public String getResumeurl() {
        return resumeurl;
    }

    public void setResumeurl(String resumeurl) {
        this.resumeurl = resumeurl == null ? null : resumeurl.trim();
    }

    

    public String getSkilledcheck() {
		return skilledcheck;
	}

	public void setSkilledcheck(String skilledcheck) {
		this.skilledcheck = skilledcheck;
	}

	public String getWantchecktheme() {
        return wantchecktheme;
    }

    public void setWantchecktheme(String wantchecktheme) {
        this.wantchecktheme = wantchecktheme == null ? null : wantchecktheme.trim();
    }

    public String getWantcheckworkstype() {
        return wantcheckworkstype;
    }

    public void setWantcheckworkstype(String wantcheckworkstype) {
        this.wantcheckworkstype = wantcheckworkstype == null ? null : wantcheckworkstype.trim();
    }

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

  
}