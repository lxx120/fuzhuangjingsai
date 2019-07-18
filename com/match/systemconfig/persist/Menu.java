package com.match.systemconfig.persist;

import java.io.Serializable;

import com.match.systemconfig.enums.MenutypeEnum;

/**
 * 目录菜单
 * 
 * @author admin
 *
 */
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String title;
	
	private String icon;
	
	private String url;
	
	private boolean valid;
	
	private String target;// target
	
	private long permission; // 没用
	
	private long pid;
	
	private int lft;
	
	private int rgt;
	
	private int level;// 从1开始
	
	private int ordering;
	
	private long authorObject;  //菜单对应的权限对象名称
	
	private int authorOpValue;//对应的操作
	
	private boolean needAuth=true;//需要权限,默认都是需要权限的
	
	private MenutypeEnum menutype;//菜单类型，用于客户端，管理端的不同的菜单
	
	private long module;// 所属模块

	
	
	public boolean isNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public long getPermission() {
		return permission;
	}

	public void setPermission(long permission) {
		this.permission = permission;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public int getLft() {
		return lft;
	}

	public void setLft(int lft) {
		this.lft = lft;
	}

	public int getRgt() {
		return rgt;
	}

	public void setRgt(int rgt) {
		this.rgt = rgt;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	
	public MenutypeEnum getMenutype() {
		return menutype;
	}

	public void setMenutype(MenutypeEnum menutype) {
		this.menutype = menutype;
	}

	public long getModule() {
		return module;
	}

	public void setModule(long module) {
		this.module = module;
	}

	public long getAuthorObject() {
		return authorObject;
	}

	public void setAuthorObject(long authorObject) {
		this.authorObject = authorObject;
	}

	public int getAuthorOpValue() {
		return authorOpValue;
	}

	public void setAuthorOpValue(int authorOpValue) {
		this.authorOpValue = authorOpValue;
	}
	
	


}
