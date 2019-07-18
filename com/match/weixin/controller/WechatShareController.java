package com.match.weixin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.weixin.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/merchant/wechatShare")
public class WechatShareController {

	@RequestMapping("/getsign.htm")
	@ResponseBody
	public String getsign(HttpServletRequest r, HttpServletResponse resp){
		String url = RequestParam.getString(r, "url");
		System.out.println("______________________url :"+url);
		Map<String, String> map = WeixinShare.sign(url);
		System.out.println("______________________signMap :"+Jacksonmethod.tojson_date(map, false));
		SessionMethod.writeresp(resp, Jacksonmethod.tojson_date(map, false));
		return null;
	}

}
