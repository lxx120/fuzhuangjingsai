package com.match.controllers.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.Zhuanbianduixiang;
import com.match.common.result.ObjectResult;
import com.match.org.model.HuoJiangZhengShu;
import com.match.org.service.PdfService;

@Controller
@RequestMapping(value = "/PdfFile")
public class Pdf {

	@Autowired
	private PdfService PdfService;

	@RequestMapping("/PdfFile.htm")
	@ResponseBody
	private String PdfFile(HttpServletRequest request, HttpServletResponse response) {
		// 判断文件是否为空
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		if (getlogin != null) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("competitionname", getlogin.getComname());
				Long id = RequestParam.getLong(request, "id");
				String fileName = RequestParam.getString(request, "twcode");
				
				String result1 = PdfService.addPDFBaoMing(map, id);
//				result1 = java.net.URLDecoder.decode(result1, "ISO-8859-1");// 如果跨域需设置解码
				ByteArrayInputStream inStream = new ByteArrayInputStream(result1.getBytes("ISO-8859-1"));
				// 设置输出的格式
				response.setContentType("bin");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
				// 循环取出流中的数据
				byte[] b = new byte[2048];
				int len;
				try {
					while ((len = inStream.read(b)) > 0)
						response.getOutputStream().write(b, 0, len);
						inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
//		String tojson = Jacksonmethod.tojson(result, false);
//		SessionMethod.writerespstr(response, tojson);
		return null;
	}
	
	
	
	@RequestMapping("/PdfZhengshuFile.htm")
	@ResponseBody
	private String PdfZhengshuFile(HttpServletRequest request, HttpServletResponse response) {
		// 判断文件是否为空
		ObjectResult<Map<String, Object>> result = new ObjectResult<>();
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		if (getlogin != null) {
			try {
				HuoJiangZhengShu huoJiangZhengShu = new Zhuanbianduixiang().reflects(request, HuoJiangZhengShu.class);
				huoJiangZhengShu.setCompetitionid(getlogin.getCompetitionid());
				String fileName = huoJiangZhengShu.getStwcode();
				
				String result1 = PdfService.PdfZhengshuFile(huoJiangZhengShu);
//				result1 = java.net.URLDecoder.decode(result1, "ISO-8859-1");// 如果跨域需设置解码
				ByteArrayInputStream inStream = new ByteArrayInputStream(result1.getBytes("ISO-8859-1"));
				// 设置输出的格式
				response.setContentType("bin");
				response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
				// 循环取出流中的数据
				byte[] b = new byte[2048];
				int len;
				try {
					while ((len = inStream.read(b)) > 0)
						response.getOutputStream().write(b, 0, len);
						inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result.setCode(1);
			result.setErrmsg("尚未登录，无法获取数据");
		}
//		String tojson = Jacksonmethod.tojson(result, false);
//		SessionMethod.writerespstr(response, tojson);
		return null;
	}
}
