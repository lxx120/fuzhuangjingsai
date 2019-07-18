package com.match.controllers.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.Student;
import com.match.baciss.model.Teacher;
import com.match.baciss.model.User;
import com.match.baciss.model.Worker;
import com.match.baciss.service.UserServices;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;

@Controller
@RequestMapping(value = "/Webmember")
public class UserWebController {

	 @Resource
	 private UserServices userService;
	
	// 登录
		@RequestMapping(value = "/doLogin.htm")
		@ResponseBody
		public String doLogin(HttpServletRequest r, HttpServletResponse re) throws Exception {
			String account = RequestParam.getSqlString(r, "account");
			String password = RequestParam.getSqlString(r, "password");
			String valicode = RequestParam.getSqlString(r, "valicode");
			HttpSession session = r.getSession();
			String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			ObjectResult<LoginMessage> orr = new ObjectResult<LoginMessage>();

//			if (code.equals(valicode)) {
				orr = userService.dologinWeb(account,password);
				if (orr.getCode() == 0) {
					boolean bo = SessionMethod.setlogin(r.getSession(), orr.getItem());
				}
//			} else {
//				orr.setCode(1);
//				orr.setErrmsg("验证码错误");
//			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		@RequestMapping(value = "/updatePasswordWeb.htm")
		@ResponseBody
		public String updatePasswordWeb(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				String password = RequestParam.getString(r, "password");
				String sepassword = RequestParam.getString(r, "sepassword");
				String oldpassword = RequestParam.getString(r, "oldpassword");
				int i = userService.updatePassword(getlogin.getId(), oldpassword, password, sepassword);
				if(i==1)
				{
					result.setCode(0);
					result.setErrmsg("修改成功");
				}
				else if(i==2)
				{
					result.setCode(1);
					result.setErrmsg("密码没有输入");
				}
				else if(i==3)
				{
					result.setCode(1);
					result.setErrmsg("重复密码没有输入");
				}
				else if(i==4)
				{
					result.setCode(1);
					result.setErrmsg("两次输入密码不同");
				}
				else if(i==5)
				{
					result.setCode(1);
					result.setErrmsg("新密码与原密码一致");
				}
				else if(i==7)
				{
					result.setCode(1);
					result.setErrmsg("原始密码不正确");
				}
				else
				{
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		@RequestMapping(value = "/findTeacherInformatation.htm")
		@ResponseBody
		public String findTeacherInformatation(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
					Map<String,Object> map = userService.findTeacherInformatation(getlogin.getId());
					orr.setItem(map);
					orr.setCode(0);
					orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("尚未登录，无法获取数据");
			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		@RequestMapping(value = "/findTeacherInformatation1.htm")
		@ResponseBody
		public String findTeacherInformatation1(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
					long id = RequestParam.getLong(r, "id");
					Map<String,Object> map = userService.findTeacherInformatation(id);
					orr.setItem(map);
					orr.setCode(0);
					orr.setErrmsg("查询成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("尚未登录，无法获取数据");
			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		@RequestMapping(value = "/findEnterpriseInformation.htm")
		@ResponseBody
		public String findEnterpriseInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
					Map<String,Object> map = userService.findEnterpriseInformation(getlogin.getId());
					orr.setCode(0);
					orr.setItem(map);
			} else {
				orr.setCode(1);
				orr.setErrmsg("尚未登录，无法获取数据");
			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		
		@RequestMapping(value = "/findTeacherInfo.htm")
		@ResponseBody
		public String findTeacherInfo(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//			if (getlogin != null) {
				long userid = RequestParam.getLong(r, "id");
				if (0 == userid) {
					orr.setCode(1);
					orr.setErrmsg("用户id不能为空");
				} else {
					Map<String,Object> map = userService.findTeacherInfo(userid);
					orr.setItem(map);
				}
//			} else {
//				orr.setCode(1);
//				orr.setErrmsg("尚未登录，无法获取数据");
//			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		
		@RequestMapping(value = "/updateTeacherPerfect.htm")
		@ResponseBody
		public String updateTeacherPerfect(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
				Teacher teacher = new Zhuanbianduixiang().reflects(r, Teacher.class);
				long teacherid = RequestParam.getLong(r, "teacherid");
				teacher.setId(teacherid);
				int i = userService.updateTeacherPerfect(teacher, user);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("修改成功");
				} 
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		@RequestMapping(value = "/updateUserPhoto.htm")
		@ResponseBody
		public String updateUserPhoto(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
//				long roleid = RequestParam.getLong(r, "roleid");
				user.setId(getlogin.getId());
				int i = userService.updateUserPhoto(user);
				if (i > 0) {
					result.setCode(0);
					result.setErrmsg("修改成功");
				} else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		@RequestMapping(value = "/findStudentInfomatation.htm")
		@ResponseBody
		public String findStudentInfomatation(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//			if (getlogin != null) {
				long userid = RequestParam.getLong(r, "id");
				if (0 == userid) {
					orr.setCode(1);
					orr.setErrmsg("用户id不能为空");
				} else {
					Map<String,Object> map = userService.findStudentInfomatation(userid);
					orr.setItem(map);
				}
//			} else {
//				orr.setCode(1);
//				orr.setErrmsg("尚未登录，无法获取数据");
//			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		
		@RequestMapping(value = "/updateStudentPerfect.htm")
		@ResponseBody
		public String updateStudentPerfect(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
				user.setId(getlogin.getId());
				Student student = new Zhuanbianduixiang().reflects(r, Student.class);
//				long roleid = RequestParam.getLong(r, "roleid");
				int i = userService.updateStudentPerfect(student, user);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("修改成功");
				} 
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		@RequestMapping(value = "/AddCollegeStaff.htm")
		@ResponseBody
		public String AddCollegeStaff(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
//			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
//				long roleid = RequestParam.getLong(r, "roleid");
				int i = userService.AddStudentStaff(user);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} 
				else if(i==2)
				{
					result.setCode(1);
					result.setErrmsg("手机号已存在");
				}
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
//			} else {
//				result.setCode(1);
//				result.setErrmsg("尚未登录，无法获取数据");
//			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		@RequestMapping(value = "/AddCollegeStaff1.htm")
		@ResponseBody
		public String AddCollegeStaff1(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
				Teacher teacher = new Zhuanbianduixiang().reflects(r, Teacher.class);
				user.setEnterpriseid(getlogin.getEnterpriseid());
				int i = userService.addSchoolStaff(user,teacher);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} 
				else if(i==2)
				{
					result.setCode(1);
					result.setErrmsg("手机号已存在");
				}
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		

		@RequestMapping(value = "/AddCompanyStaff.htm")
		@ResponseBody
		public String AddCompanyStaff(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
				user.setEnterpriseid(getlogin.getEnterpriseid());
				Worker worker = new Zhuanbianduixiang().reflects(r, Worker.class);
//				long roleid = RequestParam.getLong(r, "roleid");
				int i = userService.AddCompanyStaff(user, worker);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} 
				else if(i==2)
				{
					result.setCode(1);
					result.setErrmsg("手机号已存在");
				}
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		@RequestMapping(value = "/updateEnterprisePerfect.htm")
		@ResponseBody
		public String updateEnterprisePerfect(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				User user = new Zhuanbianduixiang().reflects(r, User.class);
				user.setId(getlogin.getId());
				user.setType(getlogin.getUsertype());
//				Enterprise enterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
				int i = userService.updateEnterprisePerfect(user);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("修改成功");
				} 
				else {
					result.setCode(1);
					result.setErrmsg("修改失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		
		@RequestMapping(value = "/finduserInfo.htm")
		@ResponseBody
		public String finduserInfo(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
					Map<String,Object> map = userService.finduserInfo(getlogin.getId());
					orr.setCode(0);
					orr.setItem(map);
			} else {
				orr.setCode(1);
				orr.setErrmsg("尚未登录，无法获取数据");
			}
			String str = "";
			str = Jacksonmethod.tojson(orr, false);
			SessionMethod.writerespstr(re, str);
			return null;
		}
		
		
		@RequestMapping(value = "/findSchoolStaff.htm")
		@ResponseBody
		public String findSchoolStaff(HttpServletRequest r, HttpServletResponse re) throws Exception {
			PageResult<Map<String, Object>> result = new PageResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				PageModel pageModel = new Pageparam().getPageParam(r);
				String realname = RequestParam.getString(r, "realname");
				result = userService.findSchoolStaff(getlogin.getEnterpriseid(), realname, pageModel);
				if (result.getItems() != null && result.getItems().size() > 0) {
					result.setCode(0);
					result.setErrmsg("查询成功");
				} else {
					result.setCode(1);
					result.setErrmsg("暂无数据");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
		
		
		@RequestMapping(value = "/deleteSchooleStaff.htm")
		@ResponseBody
		public String deleteSchooleStaff(HttpServletRequest r, HttpServletResponse re) throws Exception {
			ObjectResult<Map<String, Object>> result = new ObjectResult<>();
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
				long id = RequestParam.getLong(r, "id");
				long teacherid = RequestParam.getLong(r, "teacherid");
				int i  = userService.deleteSchooleStaff(id,teacherid);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("删除成功");
				} else {	
					result.setCode(1);
					result.setErrmsg("删除失败");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("尚未登录，无法获取数据");
			}

			String tojson = Jacksonmethod.tojson(result, false);
			SessionMethod.writerespstr(re, tojson);
			return null;
		}
}
