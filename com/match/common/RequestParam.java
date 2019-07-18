package com.match.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * httpservletrequest。getparameter，并转换为响应的类型
 * @author Administrator
 *
 */
public class RequestParam {
	public static int getInt(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		int i=0;
		try {
			i=Integer.valueOf(tstr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	public static long getLong(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		if(tstr==null)
			return 0;
		long i=0;
		try {
			i=Long.valueOf(tstr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	public static double getDouble(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		if(tstr==null)
			return 0;
		double i=0;
		try {
			i=Double.valueOf(tstr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}
	public static Date getDate(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		Date tt=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tt=sdf.parse(tstr);
		} catch (Exception e) {

			try{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				tt=sdf.parse(tstr);
			}catch(Exception ee){
				try{
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					tt=sdf.parse(tstr);
				}catch(Exception eee){
					try{
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
						tt=sdf.parse(tstr);
					}catch(Exception eeee){
						
					}
				}
			}
		}
		return tt;
	}
	public static Timestamp Timestamp(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		Timestamp tt=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tt=new Timestamp(sdf.parse(tstr).getTime());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tt;
	}
	public static Time getTime(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		Time tt=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
			tt=new Time(sdf.parse(tstr).getTime());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tt;
	}
	public static boolean getBool(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		try {
			if("true".equals(tstr)||"1".equals(tstr.trim()))
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static String getString(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		if(tstr!=null)
			tstr=tstr.trim();
		return tstr;
	}
	public static String getSqlString(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		if(tstr!=null)
			tstr=BasicMethod.sqlformat(tstr);
		return tstr;
	}
	public static String getURLString(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		if(tstr==null||tstr.equals(""))
			return "";
		try {
			String tstr1=new String(tstr.getBytes("ISO-8859-1"), "UTF-8");
			return tstr1.trim();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tstr;
	}
	/*
	 * <script language="JavaScript">
document.write(encodeURI('http://www.blogjava.net/chenlb/abc 中文'));
</script>

String url = "http://www.blogjava.net/chenlb/abc%20%E4%B8%AD%E6%96%87";
        try {
            System.out.println(URLDecoder.decode(url, "UTF-8"));
            System.out.println(URLDecoder.decode(url, "GBK"));//乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	 */
	public static String getDecodeStr(HttpServletRequest request,String name)
	{
		String tstr=request.getParameter(name);
		try {
			tstr= URLDecoder.decode(tstr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tstr;
	}
	public static <T extends Enum<T>> T getenum(HttpServletRequest r,String name,Class<T> clazz)
	{
		String tstr=r.getParameter(name);
		T t=null;
		try {
			t=Enum.valueOf(clazz, tstr);
		} catch (Exception e) {
		}
		return t;
	}
	public static <T> T getobj(HttpServletRequest r,T obj)
	{
		@SuppressWarnings("rawtypes")
		Class cls=obj.getClass();
		try {
			Field[] fs=cls.getDeclaredFields();
			for (Field field : fs) {
				field.setAccessible(true);
				String fn=field.getName();
				if(field.getType()==String.class)
				{
					String t=RequestParam.getString(r, fn);
					if(t!=null)
						field.set(obj, t);
				}else if(field.getType()==Timestamp.class)
				{
					Timestamp tt=RequestParam.Timestamp(r, fn);
					if(tt!=null)
						field.set(obj, tt);
				}else if(field.getType()==Date.class)
				{
					Date dt=RequestParam.getDate(r, fn);
					if(dt!=null)
						field.set(obj, dt);
				}else if(field.getType()==int.class||field.getType()==Integer.class)
				{
					int i=getInt(r, fn);
					if(i!=0)
						field.setInt(obj, i);
				}else if(field.getType()==double.class||field.getType()==Double.class)
				{
					Double d=getDouble(r, fn);
					if(Math.abs(d)>0.0001)
						field.setDouble(obj, d);
				}else if(field.getType()==long.class||field.getType()==Long.class)
				{
					Long l=getLong(r, fn);
					if(l!=0)
						field.setLong(obj, l);
				}else if(field.getType()==Boolean.class||field.getType()==boolean.class)
				{
					Boolean b=RequestParam.getBool(r, fn);
					field.setBoolean(obj, b);
				}
				//System.out.println(field.getType().toString()+(field.getType()==int.class));
			}
		} catch (IllegalAccessException e) {
		}
		return obj;
	}
}
