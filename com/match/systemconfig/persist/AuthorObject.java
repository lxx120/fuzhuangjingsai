package com.match.systemconfig.persist;

import java.io.Serializable;
/**
 * 
 * @author mengly
 *
 */
public class AuthorObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	
	private String note;
	
	private long module;
	

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

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}
	
	

}
