package com.match.org.model;

import java.util.Date;

public class Enterprise {
    private Long id;

    private Long creator;

    private Long modifier;

    private Date ctime;

    private Date mtime;

    private String name;

    private String address;

    private String people;

    private String contactor;

    private String province;

    private String city;

    private String ucno;
    
    private String major;
    
    private String  content;
    
    private String answeringquestionpath;
    
    private String publicaddresspath;
    
    private String enterprisephoto;
    
    private String enqq;
    
    
    
    public String getEnqq() {
		return enqq;
	}

	public void setEnqq(String enqq) {
		this.enqq = enqq;
	}

	public String getEnterprisephoto() {
		return enterprisephoto;
	}

	public void setEnterprisephoto(String enterprisephoto) {
		this.enterprisephoto = enterprisephoto;
	}

	public String getAnsweringquestionpath() {
		return answeringquestionpath;
	}

	public void setAnsweringquestionpath(String answeringquestionpath) {
		this.answeringquestionpath = answeringquestionpath;
	}

	public String getPublicaddresspath() {
		return publicaddresspath;
	}

	public void setPublicaddresspath(String publicaddresspath) {
		this.publicaddresspath = publicaddresspath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor == null ? null : contactor.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getUcno() {
        return ucno;
    }

    public void setUcno(String ucno) {
        this.ucno = ucno == null ? null : ucno.trim();
    }

}