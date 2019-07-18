package com.match.controllers.manager;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.Enterprise;
import com.match.org.service.EnterpriseService;

@Controller
@RequestMapping(value = "/Enterprise")
public class EnterpriseController {

	@Resource
	private EnterpriseService enterpriseService;

	@RequestMapping(value = "/deleteEnterprise.htm")
	@ResponseBody
	public String deleteEnterprise(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			boolean flag = enterpriseService.deleteEnterprise(id);
			if (flag) {
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			} else {
				orr.setCode(1);
				orr.setErrmsg("删除失败");
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

	@RequestMapping(value = "/findEnterpriseById.htm")
	@ResponseBody
	public String findEnterpriseById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			if (0 == id) {
				orr.setCode(1);
				orr.setErrmsg("企业id不能为空");
			} else {
				Map<String, Object> map = enterpriseService.findEnterpriseById(id);
				orr.setCode(0);
				orr.setItem(map);
				orr.setErrmsg("查询成功");
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

	@RequestMapping(value = "/findEnterprisePage.htm")
	@ResponseBody
	public String findEnterprisePage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			Enterprise exEnterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
			result = enterpriseService.findEnterprisePage(exEnterprise, pageModel);
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

	@RequestMapping(value = "/updateEnterprise.htm")
	@ResponseBody
	public String updateEnterprise(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Enterprise enterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
			String phone = RequestParam.getString(r, "phone");
			long userid = RequestParam.getLong(r, "userid");
			int i = enterpriseService.updateEnterprise(enterprise,phone,userid);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			}
			else if(i==2)
			{
				result.setCode(1);
				result.setErrmsg("手机号注册过");
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

	@RequestMapping(value = "/addEnterprise.htm")
	@ResponseBody
	public String addEnterprise(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Enterprise enterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
			String phone = RequestParam.getString(r, "phone");
			int i = enterpriseService.addEnterprise(enterprise, phone);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else if(i==2) {
				result.setCode(1);
				result.setErrmsg("手机号已注册");
			}
			else
			{
				result.setCode(1);
				result.setErrmsg("添加失败");
			}

		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}

	@RequestMapping(value = "/findDivisionToCompany.htm")
	@ResponseBody
	public String findDivisionToCompany(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			Enterprise exEnterprise = new Zhuanbianduixiang().reflects(r, Enterprise.class);
			exEnterprise.setId(getlogin.getDivisionid());
			result = enterpriseService.findDivisionToCompany(exEnterprise, pageModel);
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

	@RequestMapping(value = "/findEnterpriseToTheme.htm")
	@ResponseBody
	public String findEnterpriseToTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long themeid = RequestParam.getLong(r, "themeid");
			if (0 == themeid) {
				orr.setCode(1);
				orr.setErrmsg("id不能为空");
			} else {
				Map<String, Object> map = enterpriseService.findEnterpriseToTheme(themeid);
				if (map != null) {
					orr.setCode(0);
					orr.setItem(map);
					orr.setErrmsg("查询成功");
				} else {
					orr.setCode(0);
					orr.setItem(map);
					orr.setErrmsg("暂无数据");
				}

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
}
