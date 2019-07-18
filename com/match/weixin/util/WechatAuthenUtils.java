package com.match.weixin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.match.common.JSONobj;
import com.match.weixin.persist.Wechat;

public class WechatAuthenUtils {
	public enum Scope{snsapi_base,snsapi_userinfo};
	public enum UserInfoLang{zh_CN ,zh_TW,en };
	public static String getAuthenCodeUrl(Scope scope,String redirect_uri,String state){
		try {
			redirect_uri=URLEncoder.encode(redirect_uri, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatUtils.appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope="+scope+"&state="+state+"#wechat_redirect";
//		String rec=Wxhtmethod.ssl_get(url);
//		return rec;
		return url;
	}
	
	/**
	 * access_token
	 * expires_in
	 * refresh_token
	 * unionid
	 * openid
	 * @param code
	 * @return
	 */
	public static Wechat getAuthenAccessToken(String code){
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatUtils.appid+"&secret="+WechatUtils.secret+"&code="+code+"&grant_type=authorization_code";
		String rec=Wxhtmethod.ssl_get(url);//
		JsonParser jp=new JsonParser();
		try{
			JsonElement je=jp.parse(rec);
			JsonObject jo=je.getAsJsonObject();
			if("40029".equals(JSONobj.getstring(jo, "errcode")))
			{
				return null;
			}
			Wechat wechat=new Wechat();
			String access_token=JSONobj.getstring(jo, "access_token");
			wechat.firstAccessToken(access_token, JSONobj.getstring(jo, "refresh_token"));
			wechat.setOpenid(JSONobj.getstring(jo, "openid"));
			wechat.setUnionid(JSONobj.getstring(jo, "unionid"));
			return wechat;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, String> refresh_useracctoken(String refresh_token)
	{
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?");
		sb.append("appid="+WechatUtils.appid);
		sb.append("&grant_type=refresh_token");
		sb.append("&refresh_token="+refresh_token);
		sb.append("&grant_type=authorization_code");
		String rec=Wxhtmethod.ssl_get(sb.toString());//
		JsonParser jp=new JsonParser();
		try{
			Map<String, String> map=new HashMap<String, String>();
			JsonElement je=jp.parse(rec);
			JsonObject jo=je.getAsJsonObject();
			map.put("access_token", JSONobj.getstring(jo, "access_token"));
			map.put("refresh_token", JSONobj.getstring(jo, "refresh_token"));
			map.put("openid", JSONobj.getstring(jo, "openid"));
			return map;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Wechat userinfo(String access_token,String openid,UserInfoLang lang)
	{
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/sns/userinfo?");
		sb.append("access_token="+access_token);
		sb.append("&openid="+openid);
		sb.append("&lang="+lang);
		String rec=Wxhtmethod.ssl_get(sb.toString());//
		JsonParser jp=new JsonParser();
		Wechat wc=new Wechat();
		try{
			JsonElement je=jp.parse(rec);
			JsonObject jo=je.getAsJsonObject();
			wc.setOpenid(JSONobj.getstring(jo, "openid"));
			wc.setNickName(JSONobj.getstring(jo, "nickname"));
			wc.setCity(JSONobj.getstring(jo, "city"));
			wc.setCountry(JSONobj.getstring(jo, "country"));
			wc.setHeadimgurl(JSONobj.getstring(jo, "headimgurl"));
			wc.setProvince(JSONobj.getstring(jo, "province"));
			wc.setSex(JSONobj.getint(jo, "sex")==1?"男":"女");
			return wc;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}
