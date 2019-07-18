package com.match.systemconfig.persist;

import java.io.Serializable;

import com.match.systemconfig.enums.ResourceType;

/**
 * authorObject+operation===>authorResource===menu
 * 
 * 如何判断权限,user->role->module+op
 *           url->resource->obj+op->module+op
 * 即可判断出是否具有相应权限
 * 
 * 获取菜单: user->role->module+op->menus
 * 
 * 
 * @author Administrator
 * @desc 系统资源
 */
public class AuthorResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;

	private ResourceType type;// 资源类型

	private long module;// 资源模块编号

	private String uri;// 资源唯一标识号,如果是页面元素，则可以使用uuid,如果是页面，url,如果是其他，也可以用uuid
	
	private String note;

	private int authorOperation; // 所属操作

	private long authorObject;   // 所属操作对象
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getAuthorOperation() {
		return authorOperation;
	}

	public void setAuthorOperation(int authorOperation) {
		this.authorOperation = authorOperation;
	}

	public long getAuthorObject() {
		return authorObject;
	}

	public void setAuthorObject(long authorObject) {
		this.authorObject = authorObject;
	}
	
	

}