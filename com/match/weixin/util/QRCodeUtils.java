package com.match.weixin.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.match.common.JSONobj;
import com.match.common.result.ObjectResult;

public class QRCodeUtils {
	public static ObjectResult<String> genQRCode(String qrscene_){
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+WechatUtils.access_token();
		JsonObject jo=new JsonObject();
		jo.addProperty("action_name", "QR_LIMIT_STR_SCENE");
		JsonObject jo1=new JsonObject();
		JsonObject jo2=new JsonObject();
		jo.add("action_info",jo1 );
		jo1.add("scene", jo2);
		jo2.addProperty("scene_str", qrscene_);
		Gson g=new Gson();
		String body=g.toJson(jo);
		String result=Wxhtmethod.ssl_postbody(url, body);
		JsonParser jp=new JsonParser();
		ObjectResult<String> or=new ObjectResult<String>();
		try{
			
			JsonObject jor=jp.parse(result).getAsJsonObject();
			String ticket=JSONobj.getstring(jor, "ticket");
			or.setCode(0);
			or.setItem(ticket);
		}catch(Exception e){
			e.printStackTrace();
			or.setCode(1);
			or.setHint(e.getMessage());
		}
		return or;
		
	}
}
