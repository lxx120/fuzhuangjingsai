package com.match.data.author.persist;

import java.io.Serializable;

/**
 * 在数据权限中，需要常用的字段,
 * 再定义数据权限规则的时候，
 * 
 * 数据权限，应该在service里面实现好用，比在mapper里面方便
 * @author mengly
 *
 */
public class TableField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String rulesID;
		
	private String name;//
	
	private String tableName;
	
	private String fieldName;
	
	private String sname;//sql 缩写
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public String getRulesID() {
		return rulesID;
	}

	public void setRulesID(String rulesID) {
		this.rulesID = rulesID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	

}
