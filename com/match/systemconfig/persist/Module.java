package com.match.systemconfig.persist;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 * 
 * @desc 模块
 * 
 */
public class Module implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String code;// 编号
	
	private String moduleName;// 资源模块名称
	
	private String moduleDesc;// 资源描述
	
	private long dependsOn;// 依赖模块

	public long getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(long dependsOn) {
		this.dependsOn = dependsOn;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}