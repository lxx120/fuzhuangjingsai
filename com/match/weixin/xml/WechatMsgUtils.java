package com.match.weixin.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.match.weixin.persist.WechatMsg;

public class WechatMsgUtils {
	public static WechatMsg read(String msgstr)
	{
		WechatMsg msg=null;
		try {
			XMLReader xrf=XMLReaderFactory.createXMLReader();
			Msgxmlreader md=new Msgxmlreader();
			InputStream io=new ByteArrayInputStream(msgstr.getBytes("utf-8"));
			InputSource is=new InputSource(io);
			xrf.setContentHandler(md);
			xrf.parse(is);
			msg=md.getMsg();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public static String encodeTextXML(String openid,String fromUserName,String content){
		String str="<xml><ToUserName><![CDATA[%s]]></ToUserName> <FromUserName><![CDATA[%s]]></FromUserName> <CreateTime>"+new Date().getTime()+"</CreateTime> <MsgType><![CDATA[text]]></MsgType><Content><![CDATA[%s]]></Content></xml>";
		str=String.format(str, openid,fromUserName,content);
		return str;
		
	}
}
