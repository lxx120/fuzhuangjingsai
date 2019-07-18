package com.match.controllers.tiaozhuan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/SchoolTZ")
public class SchoolTiaoZhuan {

	
	@RequestMapping("/scsettings.htm")
	public String scsettings(Model m){
		return "/school-officials/account_settings";
	}
	
	@RequestMapping("/scdata.htm")
	public String scdata(Model m){
		return "/school-officials/data";
	}
	
	@RequestMapping("/scleader.htm")
	public String scleader(Model m){
		return "/school-officials/leader";
	}
	
	@RequestMapping("/scpassword.htm")
	public String scpassword(Model m){
		return "/school-officials/password";
	}
	
	@RequestMapping("/scphone.htm")
	public String scphone(Model m){
		return "/school-officials/phone";
	}
	
	@RequestMapping("/sccompetition.htm")
	public String sccompetition(Model m){
		return "/school-officials/school-competition";
	}
	
	@RequestMapping("/scwinners.htm")
	public String scwinners(Model m){
		return "/school-officials/school-winners";
	}
	
	@RequestMapping("/scteacher.htm")
	public String scteacher(Model m){
		return "/school-officials/teacher";
	}
}
