package com.match.common.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/** 
 * @author mengly 
 * @version 创建时间：2016年4月1日 下午5:30:17 
 * 类说明 
 */

public class Sort implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> ascFields;
	private List<String> descFields;
	public Sort() {
		ascFields=new ArrayList<String>();
		descFields=new ArrayList<String>();
	}
	
	public void addAscField(String field)
	{
		this.ascFields.add(field);
	}
	public void addDescFields(String field)
	{
		this.descFields.add(field);
	}

	@Override
	public String toString() {
		if(this.ascFields.isEmpty()&&this.descFields.isEmpty())
			return "";
		List<String> tstrs=new ArrayList<String>();
		for (String string : ascFields) {
			tstrs.add(string+" asc");
		}
		for (String string : descFields) {
			tstrs.add(string+" desc");
		}
		if(tstrs.isEmpty())
			return "";
		String str=" order by "+StringUtils.join(tstrs, ",");
		return str;
	}
	
	
}
