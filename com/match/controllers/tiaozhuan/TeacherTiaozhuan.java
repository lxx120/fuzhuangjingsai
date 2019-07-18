package com.match.controllers.tiaozhuan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/TeacherTZ")
public class TeacherTiaozhuan {

	
	@RequestMapping("/tedate.htm")
	public String tedate(Model m){
		return "/teacher/data";
	}
	
	@RequestMapping("/tepassword.htm")
	public String tepassword(Model m){
		return "/teacher/password";
	}
	
	@RequestMapping("/tephone.htm")
	public String tephone(Model m){
		return "/teacher/phone";
	}
	
	@RequestMapping("/tereview.htm")
	public String tereview(Model m){
		return "/teacher/review";
	}
	
	@RequestMapping("/teaccount.htm")
	public String teaccount(Model m){
		return "/teacher/teacher_account";
	}
	
	@RequestMapping("/tecompetition.htm")
	public String tecompetition(Model m){
		return "/teacher/teacher_competition";
	}
	
	@RequestMapping("/tedetail.htm")
	public String tedetail(Model m){
		return "/teacher/teacher_detail";
	}
	
	@RequestMapping("/teeditor.htm")
	public String teeditor(Model m){
		return "/teacher/teacher_editor";
	}
	
	@RequestMapping("/teintegralprize.htm")
	public String teintegralprize(Model m){
		return "/teacher/teacher_integralprize";
	}
	
	@RequestMapping("/teperfect.htm")
	public String teperfect(Model m){
		return "/teacher/teacher_perfect";
	}
	
	@RequestMapping("/teteam.htm")
	public String teteam(Model m){
		return "/teacher/teacher_team";
	}
	
	@RequestMapping("/teteamdetails.htm")
	public String teteamdetails(Model m){
		return "/teacher/teacher-teamdetails";
	}
	
}
