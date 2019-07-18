package com.match.controllers.manager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.common.result.PageResult;
import com.match.news.model.Category;
import com.match.news.service.CategoryService;

@Controller
@RequestMapping(value = "/Category")
public class CategoryController {

	@Resource
	private CategoryService CategoryService;

	@RequestMapping(value = "/deleteCategory.htm")
	@ResponseBody
	public String deleteCategory(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			boolean flag = CategoryService.deleteCategory(id);
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

	@RequestMapping(value = "/findCategoryById.htm")
	@ResponseBody
	public String findCategoryById(HttpServletRequest r, HttpServletResponse re) throws Exception {
		ObjectResult<Map<String, Object>> orr = new ObjectResult<Map<String, Object>>();
		long id = RequestParam.getLong(r, "id");
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			if (0 == id) {
				orr.setCode(1);
				orr.setErrmsg("分类id不能为空");
			} else {
				Map<String, Object> map = CategoryService.findCategoryById(id);
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

//	@RequestMapping(value="/findCollegePage.htm" )
//	@ResponseBody
//	public  String  findCollegePage(HttpServletRequest r , HttpServletResponse re) throws Exception
//	{
//		PageResult<Map<String, Object>> result = new PageResult<>();
//		Login getlogin = SessionMethod.getlogin(r.getSession());
////		if(getlogin != null) {
//			PageModel pageModel = new Pageparam().getPageParam(r);
//			College college=new Zhuanbianduixiang().reflects(r,College.class);
//			result = collegeservice.findCollegePage(college, pageModel);
//			if(result.getItems()!=null && result.getItems().size()>0)
//			{
//				result.setCode(0);
//				result.setErrmsg("查询成功");
//			}
//			else
//			{
//				result.setCode(1);
//				result.setErrmsg("暂无数据");
//			}
////		}else {
////			List list = new ArrayList<>();
////			result.setItems(list);
////			result.setCode(1);
////			result.setErrmsg("尚未登录，无法获取数据");
////		}
//		
//		String tojson = Jacksonmethod.tojson(result, false);
//		SessionMethod.writerespstr(re, tojson);
//		return null;
//	}

	@RequestMapping(value = "/updateCategory.htm")
	@ResponseBody
	public String updateCategory(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Category category = new Zhuanbianduixiang().reflects(r, Category.class);
			boolean flag = CategoryService.updateCategory(category);
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

	@RequestMapping(value = "/addCategory.htm")
	@ResponseBody
	public String addCategory(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			Category category = new Zhuanbianduixiang().reflects(r, Category.class);
			boolean flag = CategoryService.addCategory(category);
			if (flag) {
				result.setCode(0);
				result.setErrmsg("添加成功");
			} else {
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

	@RequestMapping(value = "/findCategoryList.htm")
	@ResponseBody
	public String findCategoryList(HttpServletRequest r, HttpServletResponse re) throws Exception {
		PageResult<Map<String, Object>> result = new PageResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(r.getSession());
		if (getlogin != null) {
			List<Map<String, Object>> list = CategoryService.findCategoryList();
			if (list != null && list.size() > 0) {
				result.setCode(0);
				result.setErrmsg("查询成功");
				result.setItems(list);
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

}
