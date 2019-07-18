package com.match.weixin.xml;

import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.match.weixin.penum.WechatEventType;
import com.match.weixin.penum.WechatMsgType;
import com.match.weixin.persist.WechatMsg;

public class Msgxmlreader extends DefaultHandler {
	private WechatMsg msg;

	public enum pstate {
		non, MsgType, ToUserName, FromUserName, CreateTime, Content, MsgId, PicUrl, MediaId, Format, ThumbMediaId, Location_X, Location_Y, Scale, Label, Title, Description, Url, Event, EventKey, Ticket, Latitude, Longitude, Precision, Status, ScanCodeInfo, ScanType, ScanResult, Poiname
	};
	private pstate state = pstate.non;	
	
	@Override
	public void startDocument() throws SAXException {
		msg=new WechatMsg();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if("MsgType".equals(qName))
		{
			state=pstate.MsgType;
		}
		if("ToUserName".equals(qName))
		{
			state=pstate.ToUserName;
		}
		if("FromUserName".equals(qName))
		{
			state=pstate.FromUserName;
		}
		if("CreateTime".equals(qName))
		{
			state=pstate.CreateTime;
		}
		if("Content".equals(qName))
		{
			state=pstate.Content;
		}
		if("MsgId".equals(qName))
		{
			state=pstate.MsgId;
		}
		if("PicUrl".equals(qName))
		{
			state=pstate.PicUrl;
		}
		if("MediaId".equals(qName))
		{
			state=pstate.MediaId;
		}
		if("Format".equals(qName))
		{
			state=pstate.Format;
		}
		if("ThumbMediaId".equals(qName))
		{
			state=pstate.ThumbMediaId;
		}
		if("Location_X".equals(qName))
		{
			state=pstate.Location_X;
		}
		if("Location_Y".equals(qName))
		{
			state=pstate.Location_Y;
		}
		if("Scale".equals(qName))
		{
			state=pstate.Scale;
		}
		if("Label".equals(qName))
		{
			state=pstate.Label;
		}
		if("Title".equals(qName))
		{
			state=pstate.Title;
		}
		if("Description".equals(qName))
		{
			state=pstate.Description;
		}
		if("Url".equals(qName))
		{
			state=pstate.Url;
		}
		if("Event".equals(qName))
		{
			state=pstate.Event;
		}
		if("EventKey".equals(qName)) 
		{
			state=pstate.EventKey;
		}
		if("Ticket".equals(qName))
		{
			state=pstate.Ticket;
		}
		if("Latitude".equals(qName))
		{
			state=pstate.Latitude;
		}
		if("Longitude".equals(qName))
		{
			state=pstate.Longitude;
		}
		if("Precision".equals(qName))
		{
			state=pstate.Precision;
		}
		if("Status".equals(qName))
		{
			state=pstate.Status;
		}
		
		if("ScanCodeInfo".equals(qName))
		{
			state=pstate.ScanCodeInfo;
		}
		
		if("ScanType".equals(qName))
		{
			state=pstate.ScanType;
		}
		
		if("ScanResult".equals(qName))
		{
			state=pstate.ScanResult;
		}
		
		if("Poiname".equals(qName))
		{
			state=pstate.Poiname;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		state=pstate.non;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		try{
			String str=new String(ch, start, length);
			str=str.trim();
			switch(state)
			{
			case MsgType:
				WechatEventType tg=Enum.valueOf(WechatEventType.class, str);
				msg.setEvent(tg);
				return;
			case ToUserName:
				msg.setToUserName(str);
				return;
			case FromUserName:
				msg.setFromUserName(str);
				return;
			case CreateTime:
				Long l=Long.valueOf(str);
				Date d=new Date(l);
				msg.setCreateTime(d);
				return;
			case Content:
				msg.setContent(str);
				return;
			case MsgId:
				Long l1=Long.valueOf(str);
				msg.setMsgId(l1);
				return;
			case PicUrl:
				msg.setPicUrl(str);
				return;
			case MediaId:
				msg.setMediaId(str);
				return;
			case Format:
				msg.setFormat(str);
				return;
			case ThumbMediaId:
				msg.setThumbMediaId(str);
				return;
			case Location_X:
				msg.setLocation_X(str);
				return;
			case Location_Y:
				msg.setLocation_Y(str);
				return;
			case Scale:
				msg.setScale(str);
				return;
			case Label:
				msg.setLabel(str);
				return;
			case Title:
				msg.setTitle(str);
				return;
			case Description:
				msg.setDescription(str);
				return;
			case Url:
				msg.setUrl(str);
				return;
			case Event:
				msg.setEvent(WechatEventType.valueOf(str));
				return;
			case EventKey:
				msg.setEventKey(str);
				return;
			case Ticket:
				msg.setTicket(str);
				return;
			case Latitude:
				msg.setLatitude(str);
				return;
			case Longitude:
				msg.setLongitude(str);
				return;
			case Poiname:
				msg.setPoiname(str);
				return;
				
			case Precision:
				msg.setPrecision(str);
				return;
			case Status:
				msg.setStatus(str);
				return;
				
			
			//外面那一层可以忽略	
			case ScanType:
				msg.setScanType(str);
				return;
				
			case ScanResult:
				msg.setScanResult(str);
				return;
			default:
				return;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public WechatMsg getMsg() {
		return msg;
	}

	public void setMsg(WechatMsg msg) {
		this.msg = msg;
	}
	
}
