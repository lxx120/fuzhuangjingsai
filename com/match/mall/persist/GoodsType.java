package com.match.mall.persist;

import java.io.Serializable;

public class GoodsType implements Serializable {
	
	/**
	 * 商品分类
	 */
	
	private static final long serialVersionUID = 1L;

	private long id; // 主键id
	
	private String name; // 商品分类名称
	
	private String note; // 商品分类描述

	private int lft; // 左边界
	
	private int rgt; // 右边界
	
	private int level; // 商品分类级别
	
	private long pid; // 商品分类父级id
	
	private long ordering; // 排序
	
	private int valid; // 状态（1 启用 2 禁用）

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getOrdering() {
		return ordering;
	}

	public void setOrdering(long ordering) {
		this.ordering = ordering;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
