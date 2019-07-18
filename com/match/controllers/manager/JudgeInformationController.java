package com.match.controllers.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.JudgeInformation;
import com.match.baciss.model.LoginMessage;
import com.match.baciss.model.User;
import com.match.baciss.service.JudgeInformationService;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;

@Controller
@RequestMapping(value = "/JudgeInformation")
public class JudgeInformationController {

	@Resource
	private JudgeInformationService JudgeInformationService;

	@RequestMapping(value = "/deleteJudgeInformation.htm")
	@ResponseBody
	public String deleteJudgeInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			boolean flag = JudgeInformationService.deleteJudgeInformation(id);
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

	@RequestMapping(value = "/findJudgeInformationById.htm")
	@ResponseBody
	public String findJudgeInformationById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			if (0 == id) {
				orr.setCode(1);
				orr.setErrmsg("评委id不能为空");
			} else {
				Map<String, Object> map = JudgeInformationService.findJudgeInformationById(id);
				orr.setCode(0);
				List list = new ArrayList<>();
				list.add(map);
				orr.setItem(list);
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

	@RequestMapping(value = "/findJudgeInformationPage.htm")
	@ResponseBody
	public String findJudgeInformationPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			result = JudgeInformationService.findJudgeInformationPage(judgeInformation, pageModel,
					getlogin.getDivisionid());
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

	@RequestMapping(value = "/updateJudgeInformation.htm")
	@ResponseBody
	public String updateJudgeInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			boolean flag = JudgeInformationService.updateJudgeInformation(judgeInformation);
			if (flag) {
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

	@RequestMapping(value = "/addJudgeInformation.htm")
	@ResponseBody
	public String addJudgeInformation(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			int i = JudgeInformationService.addJudgeInformation(judgeInformation, getlogin.getDivisionid());
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else if (i == 2) {
				result.setCode(1);
				result.setErrmsg("评委为全局评为，无需再次添加");
			} 
			 else if (i == -1) {
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

	@RequestMapping(value = "/findGroupToJudgeInformationPage.htm")
	@ResponseBody
	public String findGroupToJudgeInformationPage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			judgeInformation.setDivisionid(getlogin.getDivisionid());
			long groupid = RequestParam.getLong(r, "groupid");
			result = JudgeInformationService.findGroupToJudgeInformationPage(judgeInformation, pageModel,
					getlogin.getDivisionid(), groupid);
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

	/**
	 * 
	 * <p>功能描述：查询评委（分页）</p>
	 * <p>方法名：findStaffToJudgePage</p>
	 * <p>@param r
	 * <p>@param re
	 * <p>@return
	 * <p>@throws Exception</p>
	 * <p>返回类型：String</p>
	 * <p>创建日期：2019年4月17日 下午2:21:20</p>  
	 * <p>创建者：lxx</p>
	 */
	@RequestMapping(value = "/findStaffToJudgePage.htm")
	@ResponseBody
	public String findStaffToJudgePage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			PageModel pageModel = new Pageparam().getPageParam(r);
			Long orgid = RequestParam.getLong(r, "orgid");
			String name = RequestParam.getString(r, "name");
			String realname = RequestParam.getString(r, "realname");
			int org = RequestParam.getInt(r, "org");
			int status = RequestParam.getInt(r, "status");
			//查询选择的
			if(status==1)
			{
				result = JudgeInformationService.findChooseStaff(getlogin.getDivisionid(),name, org, realname, orgid, pageModel);
			}
			else
			{
				result = JudgeInformationService.findStaffToJudge(getlogin.getDivisionid(),name, org, realname, orgid, pageModel);
			}
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
	
	
	@RequestMapping(value = "/addJudgeInformationNO.htm")
	@ResponseBody
	public String addJudgeInformationNO(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			JudgeInformation judgeInformation = new Zhuanbianduixiang().reflects(r, JudgeInformation.class);
			judgeInformation.setDivisionid(getlogin.getDivisionid());
			User user = new Zhuanbianduixiang().reflects(r, User.class);
			int i = JudgeInformationService.addJudgeInformationNO(judgeInformation, user);
			if (i == 1) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else if (i == 3) {
				result.setCode(1);
				result.setErrmsg("评委为全局评为，无需再次添加");
			} 
			else if (i == 2) {
				result.setCode(1);
				result.setErrmsg("手机号已经存在");
			} 
			 else if (i == -1) {
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
	
}
