package com.match.systemconfig.persist;

import java.io.Serializable;

import com.match.systemconfig.enums.DistrictRankEnum;

/**
 * 
 * @title:
 * @desc: 国家行政区
 * @Company: souke
 * @author: ych
 * @date: 2018年2月1日
 */
public class District implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id; // 主键id

	private String name; // 名称

	private String zipcode; // 行政编码

	private DistrictRankEnum rank; // 行政区类型

	private String note; // 行政区描述

	private int lft; // 左边界

	private int rgt; // 右边界

	private int level; // 行政区级别

	private long pid; // 行政区父级id

	private long ordering = 0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrdering() {
		return ordering;
	}

	public void setOrdering(long ordering) {
		this.ordering = ordering;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public DistrictRankEnum getRank() {
		return rank;
	}

	public void setRank(DistrictRankEnum rank) {
		this.rank = rank;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
