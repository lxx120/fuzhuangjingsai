package com.match.systemconfig.persist;

import java.io.Serializable;


/**
 * 平台role
 * 
 * @author admin
 *
 */
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private int state;
	private String note;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

}
