package com.match.weixin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.match.common.Httpmethod;


@Service
public class WeixinPayUtil {
	@Resource
	private Httpmethod htp;
	/**
	 * 
	 * params:{
	 * 	out_trade_no:'',
	 * 	body:'',
	 * 	total_fee:'',
	 *  spbill_create_ip:'',
	 * }
	 * nonce_str,
	 * sign,
	 * body:'商品描述',
	 * out_trade_no,
	 * total_fee,
	 * spbill_create_ip
	 * trade_type
	 * notify_url
	 * @param appid
	 * @param mch_id
	 * @return
	 */
	public  UnifiedOrder unifiedorder(String out_trade_no,String total_fee,String body,String spbill_create_ip,String appid,String mch_id,String key,WXTradeType trade_type,String notify_url){
		UnifiedOrder uo=new UnifiedOrder();
		String nonce_str=WeixinPayUtil.nonce_str();
		Map<String, String> map=new HashMap<String, String>();
		map.put("out_trade_no",out_trade_no);
		map.put("total_fee",total_fee);
		map.put("body",body);
		map.put("spbill_create_ip",spbill_create_ip);
		map.put("appid",appid);
		map.put("mch_id",mch_id);
		map.put("trade_type",trade_type+"");
		map.put("notify_url",notify_url);
		map.put("nonce_str",nonce_str);
		String sign=WeixinPayUtil.sign(map, key);
		map.put("sign",sign);
		uo.setAttach("noattach");
		uo.setBody(body);
		uo.setDetail("nodetail");
		uo.setNonce_str(nonce_str);
		uo.setOut_trade_no(out_trade_no);
		uo.setOut_trade_no(out_trade_no);
		uo.setSign(sign);
		uo.setSpbill_create_ip(spbill_create_ip);
		uo.setTotal_fee(total_fee);
		uo.setTrade_type(trade_type);
		String res=htp.ssl_postbody(PayConfig.wx_unifiedorder_uri, uo.toString(appid, mch_id, notify_url));
		uo.parse(res, key);
		return uo;
	}
	
	public static  String sign(Map<String, String> params,String key){
		String strs=WeixinPayUtil.concatStr(params);
		if(!strs.contains("key")){
			strs=strs+"&key="+key;
		}
		return sign(strs);
	}
	
	public static String concatStr(Map<String, String> params)
	{
		List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuffer sb=new StringBuffer();
        String tempKeystr=null;
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if("key".equals(key))
            {
            	tempKeystr="key="+value;
            	continue;
            }
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
            	sb.append(key + "=" + value);
            } else {
            	sb.append(key + "=" + value+ "&");
            }
        }
        String str=sb.toString();
        if(tempKeystr!=null)
        {
        	if(str.endsWith("&"))
        		str=str+tempKeystr;
        	else
        		str=str+"&"+tempKeystr;
        }
        return str;
	}
	public static String sign(String str)
	{
		return DigestUtils.md5Hex(str).toUpperCase();
	}
	public static String nonce_str(){
		return DigestUtils.md5Hex(UUID.randomUUID().toString()).toUpperCase();
	}
	
	public static Map<String, String> parseXML(String result)
	{
		Document d=null;
		try{
			d=DocumentHelper.parseText(result);
			if(d==null)
				return null;
		    Element xml=d.getRootElement();
		    Map<String, String> map=new HashMap<String, String>();
		    @SuppressWarnings("unchecked")
			List<Element> eles=xml.elements();
		    if(eles!=null)
		    {
		    	for (Element element : eles) {
					String key=element.getName();
					String value=element.getTextTrim();
					map.put(key, value);
				}
		    }
		    return map;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}
