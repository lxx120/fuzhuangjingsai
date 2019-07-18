package com.match.common;



import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class JSONobj {
	public static JsonObject getJsonObject(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		JsonObject t=null;
		try{
			t=je.getAsJsonObject();
		}catch (Exception e) {
		}
		
		return t;
	}
	public static JsonArray getJsonArray(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		JsonArray t=null;
		try{
			t=je.getAsJsonArray();
		}catch (Exception e) {
		}		
		return t;
	}
	public static Long getlong(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		long t=0;
		try{
			t=je.getAsLong();
		}catch (Exception e) {
		}
		
		return t;
	}
	public static Double getdouble(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		double t=0;
		try{
			t=je.getAsDouble();
		}catch (Exception e) {
		}		
		return t;
	}
	public static Date getdate(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		Date t=null;
		String tt=JSONobj.getstring(jo, n);
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			t=sdf.parse(tt);
		}catch (Exception e) {
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			try{
				t=sdf1.parse(tt);
			}catch(Exception e1){
			}
		}		
		return t;
	}
	public static Integer getint(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		int t=0;
		try{
			t=je.getAsInt();
		}catch (Exception e) {
		}
		return t;
	}
	public static boolean getbool(JsonObject jo,String n)
	{
		if(jo==null)
			return false;
		JsonElement je=jo.get(n);
		if(je==null)
			return false;
		boolean t=false;
		try{
			String tt=je.getAsString();
			if(tt==null)
				return false;
			tt=tt.trim();
			if("1".equals(tt)||"true".equals(tt))
			{
				t=true;
			}
		}catch (Exception e) {
		}
		return t;
	}
	public static String getstring(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		String tt=null;
		try{
			tt=je.getAsString();
		}catch (Exception e) {
			System.out.println("get string error:"+n);
		}
		return tt;
	}
	public static String getstringnonull(JsonObject jo,String n)
	{
		if(jo==null)
			return "";
		JsonElement je=jo.get(n);
		if(je==null)
			return "";
		String tt="";
		try{
			tt=je.getAsString();
		}catch (Exception e) {
		}
		return tt;
	}
	/**
	 * timestamp/datetime
	 * @param jo
	 * @param n
	 * @return
	 */
	public static String gettmstring(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		String tt="1970-01-01 08:00:00";
		try{
			tt=je.getAsString();
		}catch (Exception e) {
//			System.out.println("get string error:"+n);
		}
		return tt;
	}
	/**
	 * date
	 * @param jo
	 * @param n
	 * @return
	 */
	public static String getdtstring(JsonObject jo,String n)
	{
		if(jo==null)
			return null;
		JsonElement je=jo.get(n);
		if(je==null)
			return null;
		String tt="1970-01-01";
		try{
			tt=je.getAsString();
		}catch (Exception e) {
//			System.out.println("get string error:"+n);
		}
		return tt;
	}
	
	public static <T> T getobj(JsonObject jo,T obj)
	{
		if(jo==null)
			return null;
		@SuppressWarnings("rawtypes")
		Class cls=obj.getClass();
		try {
			Field[] fs=cls.getDeclaredFields();
			for (Field field : fs) {
				field.setAccessible(true);
				String fn=field.getName();
				if(field.getType()==String.class)
				{
					String t=JSONobj.getstring(jo, fn);
					if(t!=null)
						field.set(obj, t);
				}else if(field.getType()==Timestamp.class)
				{
					Date d=JSONobj.getdate(jo, fn);
					if(d!=null)
					{
						Timestamp tt=new Timestamp(d.getTime());
						field.set(obj, tt);
					}
				}else if(field.getType()==Date.class)
				{
					Date dt=JSONobj.getdate(jo, fn);
					if(dt!=null)
						field.set(obj, dt);
				}else if(field.getType()==int.class||field.getType()==Integer.class)
				{
					Integer io=JSONobj.getint(jo, fn);
					if(io!=null)
					{
						int i=io;
						field.setInt(obj, i);
					}
				}else if(field.getType()==double.class||field.getType()==Double.class)
				{
					Double d=JSONobj.getdouble(jo, fn);
					if(d!=null)
					{
						double dd=d;
						field.setDouble(obj, dd);	
					}
				}else if(field.getType()==long.class||field.getType()==Long.class)
				{
					Long l=JSONobj.getlong(jo, fn);
					if(l!=null)
					{
						long ll=l;
						field.setLong(obj, ll);
					}
				}else if(field.getType()==Boolean.class||field.getType()==boolean.class)
				{
					Boolean b=JSONobj.getbool(jo, fn);
					field.setBoolean(obj, b);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
