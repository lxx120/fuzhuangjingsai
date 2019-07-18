package com.match.controllers.manager;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.news.model.ContentFile;
import com.match.news.service.ContentFileService;

@Controller
@RequestMapping(value="/ContentFile" )
public class ContentFileController {

	@Autowired
	private  ContentFileService  ContentFileService;
	
	@RequestMapping("/upload.htm")
	@ResponseBody
	private String saveFile(HttpServletRequest request,@org.springframework.web.bind.annotation.RequestParam("file") MultipartFile file, HttpServletResponse response) {
	      // 判断文件是否为空
		ObjectResult<ContentFile> result = new ObjectResult<>();
	    if (!file.isEmpty()) {
	    	try {
//	    		String filePath = request.getSession().getServletContext()
//	                  .getRealPath("/") + "upload/" + file.getOriginalFilename();
	    		System.out.println(file.getOriginalFilename());
	    		String[] strs=file.getOriginalFilename().split("\\.");
	    		String s1 = UUID.randomUUID().toString();
	    		String filePath = request.getSession().getServletContext()
		                  .getRealPath("/") + "upload/" + s1+"."+strs[1];
	    		System.out.println(file.getOriginalFilename());
	    		System.out.println(filePath);
//	    		request.getServletContext().getRealPath("/")项目在服务器上的根目录
	            File saveDir = new File(filePath);
	            if (!saveDir.getParentFile().exists())
	                  saveDir.getParentFile().mkdirs();
	              // 转存文件
	              file.transferTo(saveDir);
	              
	              long id = RequestParam.getLong(request, "id");
	              ContentFile contentFile = new ContentFile();
	              String s = "/"+"upload/"+s1+"."+strs[1];
	              contentFile.setPath(s);
	              contentFile.setFile(s1);
	              contentFile.setSize((double)file.getSize());
	              result.setItem(contentFile);
//	              contentFile.setContentid(id);
//	              ContentFileService.addContentFile(contentFile);
	              result.setCode(0);
	              result.setErrmsg("上传成功");
	        } catch (Exception e) {
	        	result.setCode(1);
		        result.setErrmsg("上传失败");
	            e.printStackTrace();
	        }
	    }
	    String tojson = Jacksonmethod.tojson(result, false);
	    SessionMethod.writerespstr(response, tojson);
	    return null;
	}
	
	
	@RequestMapping(value="/findContentFileList.htm" )
	@ResponseBody
	public  String  findContentFileList(HttpServletRequest r , HttpServletResponse re)  throws  Exception
	{
		ObjectResult<List<Map<String,Object>>>  orr = new  ObjectResult<List<Map<String,Object>>>(); 
		long id = RequestParam.getLong(r, "id");
		if(0==id)
		{
			orr.setCode(1);
			orr.setErrmsg("内容id不能为空");
		}
		else
		{
			List<Map<String,Object>>  map = ContentFileService.findContentFileList(id);
			orr.setCode(0);
			orr.setItem(map);
			orr.setErrmsg("查询成功");
		}
		String str="";
		str=Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
	
	
	@RequestMapping(value="/deleteContentFile.htm" )
	@ResponseBody
	public  String  deleteContentFile(HttpServletRequest r , HttpServletResponse re)  throws  Exception
	{
		ObjectResult<List<Map<String,Object>>>  orr = new  ObjectResult<List<Map<String,Object>>>(); 
		long id = RequestParam.getLong(r, "id");
		if(0==id)
		{
			orr.setCode(1);
			orr.setErrmsg("文件id不能为空");
		}
		else
		{
			boolean falg = ContentFileService.deleteContentFile(id);
			if(falg)
			{
				orr.setCode(0);
				orr.setErrmsg("删除成功");
			}
			else
			{
				orr.setCode(-1);
				orr.setErrmsg("删除成功");
			}
			
		}
		String str="";
		str=Jacksonmethod.tojson(orr, false);
		SessionMethod.writerespstr(re, str);
		return null;
	}
}
