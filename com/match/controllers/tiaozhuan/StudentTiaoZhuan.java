package com.match.controllers.tiaozhuan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/StudentTZ")
public class StudentTiaoZhuan {

	
	@RequestMapping("/stnumber.htm")
	public String stnumber(Model m){
		return "/student/account_number";
	}
	
	@RequestMapping("/stdata.htm")
	public String stdata(Model m){
		return "/student/data";
	}
	
	@RequestMapping("/stworks.htm")
	public String stworks(Model m){
		return "/student/modify_works";
	}
	
	@RequestMapping("/stcompetition.htm")
	public String stcompetition(Model m){
		return "/student/my_competition";
	}
	
	@RequestMapping("/stmyworks.htm")
	public String stmyworks(Model m){
		return "/student/my_works";
	}
	
	@RequestMapping("/stpassword.htm")
	public String stpassword(Model m){
		return "/student/password";
	}
	
	@RequestMapping("/stphone.htm")
	public String stphone(Model m){
		return "/student/phone";
	}
}
