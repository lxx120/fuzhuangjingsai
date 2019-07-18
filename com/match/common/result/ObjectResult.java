package com.match.common.result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
 * @author mengly 
 * @version 创建时间：2016年4月2日 下午4:01:52 
 * 类说明 
 */

public class ObjectResult<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code;  //code为0表示结果ok，非0则表示错误，不同的错误可以使用不同的错误代码
	private String hint;
	private T item;
	private String errmsg;
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	
	
	
}
