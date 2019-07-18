package com.match.systemconfig.persist;

import java.io.Serializable;
/**
 * 
 * @author mengly
 *
 * 补充rolemodule,当需要单独为某个用户设置权限的时候,存放在该地方,
 *
 */
public class UserPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long userID;
	
	private long authorObject;
	
	private int authorOperation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public long getAuthorObject() {
		return authorObject;
	}

	public void setAuthorObject(long authorObject) {
		this.authorObject = authorObject;
	}

	public int getAuthorOperation() {
		return authorOperation;
	}

	public void setAuthorOperation(int authorOperation) {
		this.authorOperation = authorOperation;
	}
	
}
