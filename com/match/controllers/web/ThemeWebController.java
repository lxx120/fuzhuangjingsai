package com.match.controllers.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.Pageparam;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageModel;
import com.match.common.result.PageResult;
import com.match.org.model.propositionstrategy;
import com.match.proposition.model.ChaXunTiaoJian;
import com.match.proposition.model.Theme;
import com.match.proposition.model.ThemeType;
import com.match.proposition.service.ThemeService;

@Controller
@RequestMapping(value = "/WebTheme")
public class ThemeWebController {

	@Autowired
	private  ThemeService  themeservice;
	
	/**
	 * 查询命题企业分页
	 * @param r
	 * @param re
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findThemepage.htm")
	@ResponseBody
	public String findThemepage(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		Theme theme = new Zhuanbianduixiang().reflects(r, Theme.class);
		result = themeservice.findThemepage(theme, pageModel);
		if (result.getItems() != null && result.getItems().size() > 0) {
			result.setCode(0);
			result.setErrmsg("存在命题企业");
		} else {
			result.setCode(1);
			result.setErrmsg("暂无数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	@RequestMapping(value = "/findThemepage1.htm")
	@ResponseBody
	public String findThemepage1(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		PageModel pageModel = new Pageparam().getPageParam(r);
		Theme theme = new Zhuanbianduixiang().reflects(r, Theme.class);
		result = themeservice.findThemepage1(theme, pageModel);
		if (result.getItems() != null && result.getItems().size() > 0) {
			result.setCode(0);
			result.setErrmsg("存在命题企业");
		} else {
			result.setCode(1);
			result.setErrmsg("暂无数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	@RequestMapping(value = "/updateTheme.htm")
	@ResponseBody
	public String updateTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Theme theme = new Zhuanbianduixiang().reflects(r, Theme.class);
			ThemeType themeType = new Zhuanbianduixiang().reflects(r, ThemeType.class);
			int i = themeservice.updateTheme(theme, themeType);
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("修改成功");
			} 
			else if(i==2) {
				result.setCode(1);
				result.setErrmsg("已经开始不能进行修改");
			}
			else if(i==5) {
				result.setCode(1);
				result.setErrmsg("还未到提交命题时间");
			}
			else if(i==7) {
				result.setCode(1);
				result.setErrmsg("作品提交开始时间不能早于比赛规定提交开始时间");
			}
			else if(i==8) {
				result.setCode(1);
				result.setErrmsg("作品提交结束时间不能晚于比赛规定提交结束时间");
			}
			else if(i==4) {
				result.setCode(1);
				result.setErrmsg("比赛尚未开始");
			}
			else if(i==6) {
				result.setCode(1);
				result.setErrmsg("命题提交时间已过");
			}
			else  {
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
	
	@RequestMapping(value = "/addTheme.htm")
	@ResponseBody
	public String addTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Theme theme = new Zhuanbianduixiang().reflects(r, Theme.class);
			theme.setEnterpriseid(getlogin.getEnterpriseid());
			theme.setCompetitionid(getlogin.getCompetitionid());
			theme.setDivisionid(getlogin.getDivisionid());
			ThemeType themeType = new Zhuanbianduixiang().reflects(r, ThemeType.class);
			propositionstrategy  propositionstrategy = new Zhuanbianduixiang().reflects(r, propositionstrategy.class);
			if(StringUtils.isBlank(themeType.getPtcode()))
			{
				result.setCode(1);
				result.setErrmsg("分类不能为空");
			}
			else
			{
				int i = themeservice.addTheme(theme, themeType,propositionstrategy);
				if (i==1) {
					result.setCode(0);
					result.setErrmsg("添加成功");
				} 
				else if(i==2) {
					result.setCode(1);
					result.setErrmsg("命题类型不能为空");
				}
				else if(i==3) {
					result.setCode(1);
					result.setErrmsg("暂无进行的比赛");
				}
				else if(i==4) {
					result.setCode(1);
					result.setErrmsg("比赛尚未开始");
				}
				else if(i==5) {
					result.setCode(1);
					result.setErrmsg("还未到提交命题时间");
				}
				else if(i==6) {
					result.setCode(1);
					result.setErrmsg("命题提交时间已过");
				}
				else if(i==7) {
					result.setCode(1);
					result.setErrmsg("作品提交开始时间不能早于比赛规定提交开始时间");
				}
				else if(i==8) {
					result.setCode(1);
					result.setErrmsg("作品提交结束时间不能晚于比赛规定提交结束时间");
				}
				else  {
					result.setCode(1);
					result.setErrmsg("添加失败");
				}
			}
			
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findTheme.htm")
	@ResponseBody
	public String findTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>>  obb = new  ObjectResult<Map<String,Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long id = RequestParam.getLong(r, "id");
			Map<String,Object> map = themeservice.findTheme(id);
			if (map!=null) {
				//当前竞赛名称
				map.put("comname", getlogin.getComname());
				obb.setCode(0);
				obb.setErrmsg("查询成功");
				obb.setItem(map);
			} 
			else  {
				obb.setCode(1);
				obb.setErrmsg("暂无数据");
			}
		} else {
			obb.setCode(1);
			obb.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(obb, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findEnterpriseJoinTheme.htm")
	@ResponseBody
	public String findEnterpriseJoinTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<List<Map<String,Object>>>  obb = new  ObjectResult<List<Map<String,Object>>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String,Object>> list = themeservice.findEnterpriseJoinTheme(getlogin.getEnterpriseid());
			if (list!=null&&list.size()>0) {
				obb.setCode(0);
				obb.setErrmsg("查询成功");
				obb.setItem(list);
			} 
			else  {
				obb.setCode(1);
				obb.setErrmsg("暂无数据");
			}
		} else {
			obb.setCode(1);
			obb.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(obb, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findEnterpriseTheme.htm")
	@ResponseBody
	public String findEnterpriseTheme(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();		
			LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
			if (getlogin != null) {
			long userid = (long)0;
			if(getlogin!=null)
			{
				userid = getlogin.getId();
			}
			PageModel pageModel = new Pageparam().getPageParam(r);
			ChaXunTiaoJian chaXunTiaoJian = new Zhuanbianduixiang().reflects(r, ChaXunTiaoJian.class);
			
			result = themeservice.findEnterpriseTheme(userid, chaXunTiaoJian, pageModel);
			if (result.getItems()!=null&&result.getItems().size()>0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
			} 
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findIsCanSignUp.htm")
	@ResponseBody
	public String findIsCanSignUp(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long themeid  = RequestParam.getLong(r, "themeid");
			Map<String,Object>  remap = new HashMap<String, Object>();
			remap.put("themeid", themeid);
			Map<String,Object>  map = themeservice.findIsCanSignUp(getlogin.getId(), themeid);
			int i = Integer.parseInt(map.get("code").toString());
			if (i==1) {
				result.setCode(0);
				result.setErrmsg("成功");
				result.setItem(remap);
			} 
			else if(i==0) {
				result.setCode(1);
				result.setErrmsg("还没有比赛");
			}
			else if(i==3) {
				result.setCode(1);
				result.setErrmsg("您不是学生无法参加");
			}
			else if(i==4) {
				result.setCode(1);
				result.setErrmsg("您还未完善资质");
			}
			else if(i==5) {
				result.setCode(1);
				result.setErrmsg("您所在的学校还未被分配在赛区中");
			}
			else if(i==6) {
				result.setCode(1);
				result.setErrmsg("不是一个赛区不能报名");
			}
			else if(i==7) {
				result.setCode(1);
				result.setErrmsg("人数超过报名上线");
			}
			else if(i==8) {
				result.setCode(2);
				result.setItem(map);
				result.setErrmsg("您已报名过");
			}
			else  {
				result.setCode(1);
				result.setErrmsg("失败");
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
	
	@RequestMapping(value = "/findThemeByEnterpriseId.htm")
	@ResponseBody
	public String findThemeByEnterpriseId(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
		PageModel pageModel = new Pageparam().getPageParam(r);
		result = themeservice.findThemeByEnterpriseId(getlogin.getCompetitionid(),getlogin.getEnterpriseid(), pageModel);
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
	
	
	
	@RequestMapping(value = "/findthemeToSchool.htm")
	@ResponseBody
	public String findthemeToSchool(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
		PageModel pageModel = new Pageparam().getPageParam(r);
		String worktype = RequestParam.getString(r, "worktype");
		long themeid = RequestParam.getLong(r, "themeid");
		result = themeservice.findthemeToSchool(worktype,themeid, pageModel);
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
	
	
	@RequestMapping(value = "/findthemeGroupByWorktype.htm")
	@ResponseBody
	public String findthemeGroupByWorktype(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String,Object>>  obb = new  ObjectResult<Map<String,Object>>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			long themeid = RequestParam.getLong(r, "themeid");
			Map<String,Object> map = themeservice.findthemeGroupByWorktype(themeid);
			if (map!=null) {
				obb.setCode(0);
				obb.setErrmsg("查询成功");
				obb.setItem(map);
			} 
			else  {
				obb.setCode(1);
				obb.setErrmsg("暂无数据");
			}
		} else {
			obb.setCode(1);
			obb.setErrmsg("尚未登录，无法获取数据");
		}

		String tojson = Jacksonmethod.tojson(obb, false);
		SessionMethod.writerespstr(re, tojson);
		return null;
	}
	
}
