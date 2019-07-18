package com.match.systemconfig.persist;

import java.io.Serializable;

/**
 * 平台role_module
 * 
 * @author admin
 *
 */
public class RoleModule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long role;
	
	private long module;
	
	private int value;//opValue

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
