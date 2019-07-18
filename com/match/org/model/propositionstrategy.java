package com.match.org.model;

import java.util.Date;

public class propositionstrategy {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String name;

    private Long enterpriseid;
    
    private Long competitionid;
    
    private String  productcontent;
    
    private String  productinfo;
    
    private  Long  themeid;
    
    private  String  targetgroups;
    
    private  String  advertepurposes;
    
    private  String  adverteform;
    
    private  String  relevantinfo;
    
    private  String  logoelement;
    
    
    
    

    public String getTargetgroups() {
		return targetgroups;
	}

	public void setTargetgroups(String targetgroups) {
		this.targetgroups = targetgroups;
	}

	public String getAdvertepurposes() {
		return advertepurposes;
	}

	public void setAdvertepurposes(String advertepurposes) {
		this.advertepurposes = advertepurposes;
	}

	public String getAdverteform() {
		return adverteform;
	}

	public void setAdverteform(String adverteform) {
		this.adverteform = adverteform;
	}

	public String getRelevantinfo() {
		return relevantinfo;
	}

	public void setRelevantinfo(String relevantinfo) {
		this.relevantinfo = relevantinfo;
	}

	public String getLogoelement() {
		return logoelement;
	}

	public void setLogoelement(String logoelement) {
		this.logoelement = logoelement;
	}

	public Long getThemeid() {
		return themeid;
	}

	public void setThemeid(Long themeid) {
		this.themeid = themeid;
	}

	

	public String getProductcontent() {
		return productcontent;
	}

	public void setProductcontent(String productcontent) {
		this.productcontent = productcontent;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public Long getCompetitionid() {
		return competitionid;
	}

	public void setCompetitionid(Long competitionid) {
		this.competitionid = competitionid;
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

    public Long getEnterpriseid() {
        return enterpriseid;
    }

    public void setEnterpriseid(Long enterpriseid) {
        this.enterpriseid = enterpriseid;
    }
}