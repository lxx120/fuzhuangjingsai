package com.match.proposition.model;

import java.util.Date;

/**
 * <p>类功能描述: </p>
 * <p>类名：Division </p>
 * <p>创建时间：2019年3月27日 下午4:46:00 </p>
 * <p>创建者：lxx</p>
 */
public class Division {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String name;

    private String phone;

    private String code;

    private Long competitionid;

    private String note;
    
    private  Integer  ditype;
    
    private  Integer  promotionnum;
    
    private  String  contator;
    
    private  Long userid;
    
    

	

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getDitype() {
		return ditype;
	}

	public void setDitype(Integer ditype) {
		this.ditype = ditype;
	}

	public Integer getPromotionnum() {
		return promotionnum;
	}

	public void setPromotionnum(Integer promotionnum) {
		this.promotionnum = promotionnum;
	}

	public String getContator() {
		return contator;
	}

	public void setContator(String contator) {
		this.contator = contator;
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

   

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getCompetitionid() {
        return competitionid;
    }

    public void setCompetitionid(Long competitionid) {
        this.competitionid = competitionid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}