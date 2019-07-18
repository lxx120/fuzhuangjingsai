package com.match.controllers.tiaozhuan;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.match.common.ConstantVar;

@Controller
@RequestMapping(value = "/webmemberTZ")
public class UserTiaoZhuan {

	
//	@RequestMapping("/wholesaler/{wholesaler}/goods/{goodsid}iew.htm")
////	@WebLogger(module="陌生人浏览",operation="查看ajax",title="查看供应商商品详情")
//	public String wholesalerGoodsView(HttpServletRequest r,@PathVariable("wholesaler")long wholesaler,@PathVariable("goodsid") long goodsid,Model m){
//		Map<String, Object> w=this.iaSer.wholesaler(wholesaler);
//		Map<String, Object> g=this.iaSer.goodsDetail(goodsid);
//		m.addAttribute("wholesaler",wholesaler);
//		m.addAttribute("baseURL", ConstantVar.fastdfs_server);
//		m.addAttribute("w", w);
//		m.addAttribute("g", g);
//		return "/anonymous/goodsdetail";
//	}
	
	@RequestMapping("/login.htm")
	public String wholesalerGoodsView(HttpServletRequest r){
		return "/login";
	}
	
	@RequestMapping("/enaccount.htm")
	public String enaccount(Model m){
		return "/enterprise/enterprise_account";
	}
	
	@RequestMapping("/enaddproposition.htm")
	public String enaddproposition(Model m){
		return "/enterprise/enterprise_addproposition";
	}
	
	@RequestMapping("/enchangepassword.htm")
	public String enchangepassword(Model m){
		return "/enterprise/enterprise_changepasssword";
	}
	
	@RequestMapping("/enhuojiangzuopin.htm")
	public String enhuojiangzuopin(Model m){
		return "/enterprise/enterprise_huojiangzuopin";
	}
	
	@RequestMapping("/eninproposition.htm")
	public String eninproposition(Model m){
		return "/enterprise/enterprise_inproposition";
	}
	
	@RequestMapping("/enperfect.htm")
	public String enperfect(Model m){
		return "/enterprise/enterprise_perfect";
	}
	
	@RequestMapping("/enproposition.htm")
	public String enproposition(Model m){
		return "/enterprise/enterprise_proposition";
	}
	
	@RequestMapping("/enviewdetail.htm")
	public String enviewdetail(Model m){
		return "/enterprise/enterprise_viewdetail";
	}
	
	@RequestMapping("/enshow.htm")
	public String enshow(Model m){
		return "/enterprise/enterprise-show";
	}
}
