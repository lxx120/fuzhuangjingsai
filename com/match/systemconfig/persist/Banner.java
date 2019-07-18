package com.match.systemconfig.persist;

import java.io.Serializable;
import java.util.Date;

import com.match.systemconfig.enums.DistrictRankEnum;

/**
 * 轮播图
 */
public class Banner implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String img;//
	private String url;//
	private String province;// 省
	private String city;// 市
	private String area;// 区
	private long provinceid;// 省ID
	private long cityid;// 市ID
	private long areaid;// 区ID
	private int lft;// 最后一级的lft
	private int rgt;// 最后一级的rgt
	private DistrictRankEnum rank;// 最后一级的行政区类型
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private int state;// 状态（1 启用 2禁用）

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLft() {
		return lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public int getRgt() {
		return rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

	public DistrictRankEnum getRank() {
		return rank;
	}

	public void setRank(DistrictRankEnum rank) {
		this.rank = rank;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(long provinceid) {
		this.provinceid = provinceid;
	}

	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public long getAreaid() {
		return areaid;
	}

	public void setAreaid(long areaid) {
		this.areaid = areaid;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}