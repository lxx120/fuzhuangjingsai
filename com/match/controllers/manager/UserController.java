package com.match.controllers.manager;

import java.awt.image.BufferedImage;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.match.baciss.cl.UserChangLiang;
import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.User;
import com.match.baciss.service.UserServices;
import com.match.common.BasicMethod;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

@Controller
@RequestMapping(value = "/member")
public class UserController {

	@Autowired
	private Producer captchaProducer = null;

	@Resource
	private UserServices userService;

	// 生成验证码
	@RequestMapping(value = "/captcha.htm", method = { RequestMethod.GET })
	public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

	// 登录
	@RequestMapping(value = "/doLogin.htm")
	@ResponseBody
	public String doLogin(HttpServletRequest r, HttpServletResponse re) throws Exception {
		String account = RequestParam.getSqlString(r, "account");
		String password = RequestParam.getSqlString(r, "password");
//		String valicode = RequestParam.getSqlString(r, "valicode");
//		HttpSession session = r.getSession();
//		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		ObjectResult<LoginMessage> orr = new ObjectResult<LoginMessage>();
//		if (code.equals(valicode)) {
			orr = userService.dologin(account,password);
			if (orr.getCode() == 0) {
				 SessionMethod.setlogin(r.getSession(), orr.getItem());
			}
//		} else {
//			orr.setCode(1);
//			orr.setErrmsg("验证码错误");
//		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/deleteUser.htm")
	@ResponseBody
	public String deleteUser(HttpServletRequest r, HttpServletResponse re) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long userid = RequestParam.getLong(r, "id");
			orr = userService.deleteUser(userid);
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/findmember.htm")
	@ResponseBody
	public String findmember(HttpServletRequest r, HttpServletResponse re) {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long userid = RequestParam.getLong(r, "id");
			if (0 == userid) {
				orr.setCode(1);
				orr.setErrmsg("用户id不能为空");
			} else {
				orr = userService.findmemeber(userid);
			}
		} else {
			orr.setCode(1);
			orr.setErrmsg("尚未登录，无法获取数据");
		}
		String str = "";
		str = Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}

	@RequestMapping(value = "/findmemberpage.htm")
	@ResponseBody
	public String findmemberpage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			User user = new Zhuanbianduixiang().reflects(r, User.class);
			result = userService.findmemberpage(user, pageModel);
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

	@RequestMapping(value = "/updatemember.htm")
	@ResponseBody
	public String updatemember(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			User user = new Zhuanbianduixiang().reflects(r, User.class);
			long roleid = RequestParam.getLong(r, "roleid");
			int i = userService.updateUser(user, roleid);
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

	@RequestMapping(value = "/addmember.htm")
	@ResponseBody
	public String addmember(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			User user = new Zhuanbianduixiang().reflects(r, User.class);
			user.setPassword(BasicMethod.encryptSHA1(UserChangLiang.password));
			long roleid = RequestParam.getLong(r, "roleid");
			if (roleid != 0) {
				int i = userService.addUser(user, roleid);
				if (i > 0) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} else if (i == 0) {
					result.setCode(1);
					result.setErrmsg("添加失败");
				} else if (i == -1) {
					result.setCode(1);
					result.setErrmsg("用户名已经注册过");
				}
			} else {
				result.setCode(1);
				result.setErrmsg("请选择正确的角色身份");
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
