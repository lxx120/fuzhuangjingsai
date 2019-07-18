package com.match.dao.common;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.match.common.JSONobj;


public class Daomethod {
	public static int countpages(int count,int pagesize)
	{
		if(pagesize==0)
			return 0;
		int pages=(count/pagesize)+((count%pagesize==0)?0:1);
		return pages;
	}
	
	/**
	 * 
	 * @param count
	 * @param pagesize
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年7月23日 下午7:28:02
	 */
	public static int countpage(int count,int pagesize)
	{
		if(pagesize==0)
			return 0;
		//int count=((Number)dao.getSQLOne_noclose(sql)).intValue();
		int page=(count/pagesize)-((count%pagesize==0)?1:0);
		return page;
	}
	
	
	
	/**
	 * 
	 * @param imgs,json字符串，[{url:"sss",name:"sfsd",size:121},{url:"sss",name:"sfsd",size:121},{url:"sss",name:"sfsd",size:121}]
	 * @param c
	 */
	public static void removefiles(String imgs,ServletContext c)
	{
		if(imgs==null||imgs.isEmpty())
			return;
		try {
			JsonParser jp=new JsonParser();
			JsonArray ja=jp.parse(imgs).getAsJsonArray();
			for (JsonElement jsonElement : ja) {
				JsonObject jo=jsonElement.getAsJsonObject();
				String url=JSONobj.getstring(jo, "url");
				String realpath=c.getRealPath(url);
				Files.delete(Paths.get(realpath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
