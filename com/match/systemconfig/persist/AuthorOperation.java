package com.match.systemconfig.persist;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * @desc 系统资源操作定义
 */
public class AuthorOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long module;//所属模块（==0 的为通用模块）
	
	private String name;
	
	private int exp;//从1开始
	
	private int value;// 1248***,2^n
	
	private String note;

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
}
