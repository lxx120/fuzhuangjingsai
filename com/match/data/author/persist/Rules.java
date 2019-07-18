package com.match.data.author.persist;

import java.io.Serializable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Rules implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;// dataAuthorID
	
	private String note;//
	
	private String rulesJson; //{rules:[{ },{},{},...],op}
	
	private int whereIndex;//从0开始,
	
	private Rule rule;
	
	private String sql;
	

	public int getWhereIndex() {
		return whereIndex;
	}

	public void setWhereIndex(int whereIndex) {
		this.whereIndex = whereIndex;
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

	public String getRulesJson() {
		return rulesJson;
	}

	public void setRulesJson(String rulesJson) {
		this.rulesJson = rulesJson;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
	
	
	

	public Rule parseRuleJson(String ruleJson){
		Rule tRule=null;
		try{
			JsonParser jp=new JsonParser();
			JsonObject jo=jp.parse(rulesJson).getAsJsonObject();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tRule;
	}
	
}
