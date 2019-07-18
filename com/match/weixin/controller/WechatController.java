package com.match.weixin.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.match.common.BasicMethod;
import com.match.common.ConstantVar;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.weixin.penum.WechatEventType;
import com.match.weixin.persist.Wechat;
import com.match.weixin.persist.WechatMsg;
import com.match.weixin.service.IWechatService;
import com.match.weixin.util.WechatUtils;
import com.match.weixin.xml.WechatMsgUtils;

@Controller
@RequestMapping("/merchant/wechat")
public class WechatController {
	private Logger log=LoggerFactory.getLogger(WechatController.class);
	@Reference
	private IWechatService iwSer;
	
	@RequestMapping(value="/receiver.htm")
	@ResponseBody
	public String receiver(HttpServletRequest r,HttpServletResponse re){
		String echostr=checkvalid(r,WechatUtils.token);
		String body=this.requestbody(r);
		if(echostr!=null&&(body==null||body.isEmpty()))
		{
			SessionMethod.writeresp(re, echostr);
			return null;
		}
		WechatMsg msg=WechatMsgUtils.read(body);
		log.info("######### the wechat msg is ::"+Jacksonmethod.tojson(msg, false));
		if(msg.getEvent()==WechatEventType.subscribe||msg.getEvent()==WechatEventType.SCAN){
			ObjectResult<Wechat> result=null;
			//
			String openid=msg.getFromUserName();
			String qrscene_=msg.getEventKey();
			log.info("the openid scan qrscent is ::::::::::::::::::::"+qrscene_);
			if(qrscene_!=null&&qrscene_.startsWith("qrscene_")){
				qrscene_=qrscene_.substring(8);
			}
			long wholesaler=0;
			try{
				wholesaler=Long.valueOf(qrscene_);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("________________________wechatScanWholesaler的 wholesaler : "+wholesaler);
			if(wholesaler!=0){
				result=iwSer.wechatScanWholesaler(openid, wholesaler);
				System.out.println("________________________wechatScanWholesaler的 msg : "+result.getHint());
				if(result!=null){
					String hint=result.getHint();
					hint+="如果您的微信号绑定的商户不是您的商户,请在公众号输入qxbd解绑微信号和账号的绑定!";
					String replyStr=WechatMsgUtils.encodeTextXML(msg.getFromUserName(), msg.getToUserName(), hint);
					log.info("%%%%%%%%%%%%%%% the wechat replystr::"+replyStr);
					SessionMethod.writeresp(re, replyStr);
				}
			}else{
				String str="恭喜您关注了便E批订货平台,请单击菜单'我要订货'进入平台开始订货,如果您没有明确的供应商，可以通过推荐或者搜索商品找到合适的供应商!!";//msg.getContent();		
				if(str!=null&&!str.isEmpty())
				{
					String replyStr=WechatMsgUtils.encodeTextXML(msg.getFromUserName(), msg.getToUserName(),str);
					SessionMethod.writeresp(re, replyStr);
				}
			}
			return null;
		}else if(msg.getEvent()==WechatEventType.text){
			String content=msg.getContent();
			if(content!=null&&"qxbd".equals(content.trim().toLowerCase())){
				ObjectResult<Boolean> rese=this.iwSer.qxbdWechWithUserid(msg.getFromUserName());
				String replyStr=WechatMsgUtils.encodeTextXML(msg.getFromUserName(), msg.getToUserName(),rese.getHint());
				SessionMethod.writeresp(re, replyStr);
				return null;
			}else{
				String str="您的留言我们会及时回复您,请耐心等待!!";//msg.getContent();		
				if(str!=null&&!str.isEmpty())
				{
					String replyStr=WechatMsgUtils.encodeTextXML(msg.getFromUserName(), msg.getToUserName(),str);
					SessionMethod.writeresp(re, replyStr);
				}
				return null;
			}
		}else{
			String str="欢迎光临便E批订货平台,请单击我要订货进入平台开始订货,如果您没有明确的供应商，可以通过推荐或者搜索商品找到合适的供应商!!";//msg.getContent();		
			if(str!=null&&!str.isEmpty())
			{
				String replyStr=WechatMsgUtils.encodeTextXML(msg.getFromUserName(), msg.getToUserName(),str);
				SessionMethod.writeresp(re, replyStr);
			}else{
				SessionMethod.writeresp(re, "success");
			}
		}	
		return null;
	}
	
	@RequestMapping("/authen/callback.htm")
	@ResponseBody
	public String auchenCallback(HttpServletRequest r,HttpServletResponse re){
//		String code = RequestParam.getString(r, "code");
//		String state=RequestParam.getSqlString(r, "state");
//		Wechat wc=WechatAuthenUtils.getAuthenAccessToken(code);
//		try{
//			//JsonObject jo=new JsonParser().parse(state).getAsJsonObject();
//			String sessionID=state;//JSONobj.getstring(jo, "sess");
//			if(wc!=null){
//				wechatPool.add(sessionID, wc);				
//			}	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		String url=ConstantVar.web_domain+"/merchant/wechat/index.htm";
//		log.info("wechat callback controller ,start to redirect to index.htm");
//		SessionMethod.sendRedirect(re, r, url);
////		SessionMethod.writerespstr(re, "success");
		return null;
	}
	
	private String checkvalid(HttpServletRequest r,String token)
	{
		if(r==null)
			return null;
		String signature=RequestParam.getSqlString(r, "signature");
		String echostr=RequestParam.getSqlString(r, "echostr");
		String timestamp=RequestParam.getSqlString(r, "timestamp");
		String nonce=RequestParam.getSqlString(r, "nonce");
		if(timestamp==null||nonce==null)
			return echostr;
		String[] array=new String[]{token,timestamp,nonce};
		Arrays.sort(array);
		String temp=StringUtils.join(array, "");
		temp=BasicMethod.encryptSHA1(temp);
		if(signature!=null&&signature.equals(temp))
		{
			return echostr;
		}
		return null;
	}
	
	private String requestbody(HttpServletRequest r)
	{
		if(r==null)
			return null;
		StringBuffer sb=new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(r.getInputStream(),Charset.forName("utf-8")));
			String line=null;
			while((line=reader.readLine())!=null)
			{
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return sb.toString();
	}
	
	@RequestMapping("/index.htm")
	public String wechatIndex(HttpServletRequest r,HttpServletResponse re){
		
		try {
			String userAgent = r.getHeader("user-agent").toLowerCase();
			if(!userAgent.contains("micromessenger")){//微信客户端
				re.sendRedirect(ConstantVar.beginLoginURL);
			}
			Wechat wc=SessionMethod.getSessionAttribute(r, ConstantVar.session_wechat_name, Wechat.class);
			Wechat wc1=this.iwSer.getWechat(wc.getOpenid());
			if (wc1 == null) {
				re.sendRedirect(ConstantVar.beginLoginURL);
			} else {
				if (wc1.getUserid() > 0) {
					re.sendRedirect(ConstantVar.beginLoginURL);
				} else {
					if (wc1.getFromMerchant() > 0) {
						re.sendRedirect("/merchant/weixin/anonymous/wholesaler/"
								+ wc1.getFromMerchant() + "/goods/begin.htm");
					} else {
						re.sendRedirect(ConstantVar.beginLoginURL);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
