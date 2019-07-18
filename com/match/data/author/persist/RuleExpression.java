package com.match.data.author.persist;

import java.io.Serializable;

import com.match.data.author.penum.RuleExpressOperation;

public class RuleExpression implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long rulesID;
	
	private long rule;
	
	private String tableName;
	
	private String fieldName;
	
	private String sname;//缩写
	
	private String value;//
	
	private boolean numberString;//true:number,false:string
	
	private RuleExpressOperation op;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRulesID() {
		return rulesID;
	}

	public void setRulesID(long rulesID) {
		this.rulesID = rulesID;
	}

	public long getRule() {
		return rule;
	}

	public void setRule(long rule) {
		this.rule = rule;
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isNumberString() {
		return numberString;
	}

	public void setNumberString(boolean numberString) {
		this.numberString = numberString;
	}

	public RuleExpressOperation getOp() {
		return op;
	}

	public void setOp(RuleExpressOperation op) {
		this.op = op;
	}
	
}
