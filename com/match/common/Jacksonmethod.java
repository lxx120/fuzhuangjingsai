package com.match.common;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jacksonmethod {
	private static ObjectMapper m=new ObjectMapper();
	private static JsonFactory jf=new JsonFactory();
	public static <T> Object fromjson(String jsonstr,Class<T> pojoclass){
		T pojo=null;
		try {
			pojo=m.readValue(jsonstr, pojoclass);
		} catch (JsonParseException e) {
			System.out.println("jackson解析错误:"+jsonstr);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pojo;
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> fromjsontolist(String jsonstr,Class<T> t){
		if(jsonstr==null)
			return null;
		m.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		JavaType javaType = m.getTypeFactory().constructParametricType(List.class, t);
		List<T> pojos=null;
		try {
			pojos=(List<T>)(List<?>)m.readValue(jsonstr, javaType);
		} catch (JsonParseException e) {
			System.out.println("jackson解析错误:");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pojos;
	}
	public static String tojson(Object pojo, boolean prettyPrint)
	{
		String str="";
		StringWriter writer=new StringWriter();
		try {
			JsonGenerator jg=jf.createGenerator(writer);
			if(prettyPrint)
				jg.useDefaultPrettyPrinter();
			m.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
			m.writeValue(jg, pojo);
			str=writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public static String tojson_date(Object pojo, boolean prettyPrint)
	{
		String str="";
		StringWriter writer=new StringWriter();
		try {
			JsonGenerator jg=jf.createGenerator(writer);
			if(prettyPrint)
				jg.useDefaultPrettyPrinter();
			m.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			m.writeValue(jg, pojo);
			str=writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	public static String tojson_datetime(Object pojo, boolean prettyPrint)
	{
		String str="";
		StringWriter writer=new StringWriter();
		try {
			JsonGenerator jg=jf.createGenerator(writer);
			if(prettyPrint)
				jg.useDefaultPrettyPrinter();
			m.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			m.writeValue(jg, pojo);
			str=writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
