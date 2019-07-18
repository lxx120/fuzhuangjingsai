package com.match.fastdfs.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.google.gson.Gson;
import com.match.common.SessionMethod;
import com.match.fastdfs.FastDFSUtils;

import sun.misc.BASE64Decoder;

/**
 * @author mengly
 * @version 创建时间：2016年4月15日 下午7:28:44 类说明
 */
@Controller("fileupload")
@RequestMapping(value = "/file/upload", method = { RequestMethod.GET, RequestMethod.POST })
public class Fileupload {
	@Resource(name = "multipartResolver")
	private CommonsMultipartResolver cmr;

	@RequestMapping(value = "/up.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String ups(HttpServletRequest r, HttpServletResponse re, @RequestParam("file") MultipartFile mf) {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson g = new Gson();
		String url = "";
		try {
			InputStream stream = mf.getInputStream();
			String[] results = FastDFSUtils.uploadFile(stream, mf.getOriginalFilename(), mf.getSize());
			if (results.length > 0) {
				String str1 = results[0];
				String str2 = results[1];
				url = str1 + "/" + str2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("url", url);
		SessionMethod.writeresp(re, g.toJson(map));
		return null;
	}

	@RequestMapping(value = "/uploadBase.htm", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String upBase(HttpServletRequest r, HttpServletResponse re) {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson g = new Gson();
		String url = "";
		//
		String imgStr = com.match.common.RequestParam.getString(r, "imgStr");
		String imgFormat = com.match.common.RequestParam.getString(r, "imgFormat");

		if (imgStr == null || "".equals(imgStr)) {
			map.put("code", 1);
			map.put("error", "请选择图片！");
			SessionMethod.writeresp(re, g.toJson(map));
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}

			InputStream stream = new ByteArrayInputStream(b);
			String[] results = FastDFSUtils.uploadFile(stream, imgStr.substring(2, 10) + "." + imgFormat,
					stream.available());
			if (results.length > 0) {
				String str1 = results[0];
				String str2 = results[1];
				url = str1 + "/" + str2;
				System.out.println(url);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("code", 0);
		map.put("url", url);
		SessionMethod.writeresp(re, g.toJson(map));
		return null;

	}
}
