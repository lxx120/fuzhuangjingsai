package com.match.controllers.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.news.model.ContentFile;
import com.match.org.service.PoiService;
import com.match.org.service.ReadFilesDirService;
import com.match.org.service.impl.ReadFilesDir;

@Controller
@RequestMapping(value = "/PoiFile")
public class Poi {

	@Autowired
	private PoiService PoiServiceImpl;

	@Autowired
	private ReadFilesDirService ReadFilesDirService;

	@RequestMapping("/writeExcle.htm")
	@ResponseBody
	private String writeExcle(HttpServletRequest request,
			@org.springframework.web.bind.annotation.RequestParam("file") MultipartFile file,
			HttpServletResponse response) {
		// 判断文件是否为空
		ObjectResult<ContentFile> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		if (getlogin != null) {
			if (!file.isEmpty()) {
				try {
					int i = PoiServiceImpl.writeExcle(file,getlogin.getDivisionid());
					if (i == 2) {
						result.setCode(1);
						result.setErrmsg("暂无比赛");
					} else if (i == 3) {
						result.setCode(1);
						result.setErrmsg("暂无轮次");
					} else {
						result.setCode(0);
						result.setErrmsg("成功");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(response, tojson);
		return null;
	}

	@RequestMapping("/file.htm")
	@ResponseBody
	private String file(HttpServletRequest request, HttpServletResponse response) {
		// 判断文件是否为空
		ObjectResult<ContentFile> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		if (getlogin != null) {
			String path = RequestParam.getString(request, "path");
			try {
				int i = ReadFilesDirService.readFilesDir(path, getlogin.getCompetitionid());
				if (i > 0) {
					result.setCode(0);
					result.setErrmsg("成功");
				} else {
					result.setCode(1);
					result.setErrmsg("失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
		String tojson = Jacksonmethod.tojson(result, false);
		SessionMethod.writerespstr(response, tojson);
		return null;
	}
}
