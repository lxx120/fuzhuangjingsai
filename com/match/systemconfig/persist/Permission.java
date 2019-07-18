package com.match.systemconfig.persist;

import java.io.Serializable;
/**
 * 权限管理
 * @author mengly
 *
 */
public class Permission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long role;
	
	private long authorObject;
	
	private int opValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public long getAuthorObject() {
		return authorObject;
	}

	public void setAuthorObject(long authorObject) {
		this.authorObject = authorObject;
	}

	public int getOpValue() {
		return opValue;
	}

	public void setOpValue(int opValue) {
		this.opValue = opValue;
	}
	
	

}
