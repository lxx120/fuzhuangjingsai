package com.match.common;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户日志
 * @author mengly
 *
 */
public class ActionLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	
	private String title;
	
	private String operateIP;
	
	private String operator;
	
	private String module;
	
	private String subject;//操作对象
	
	private Date otime;//操作时间
	
	private boolean oresult;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOperateIP() {
		return operateIP;
	}
	public void setOperateIP(String operateIP) {
		this.operateIP = operateIP;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getOtime() {
		return otime;
	}
	public void setOtime(Date otime) {
		this.otime = otime;
	}
	public boolean isOresult() {
		return oresult;
	}
	public void setOresult(boolean oresult) {
		this.oresult = oresult;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
