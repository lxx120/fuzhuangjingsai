package com.match.controllers.manager;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.match.baciss.model.LoginMessage;
import com.match.common.Jacksonmethod;
import com.match.common.RequestParam;
import com.match.common.SessionMethod;
import com.match.common.result.ObjectResult;
import com.match.judges.service.CompetitionAprizeService;
import com.match.org.utils.Excle;
import com.match.reviewresult.mapper.ExcellentCollegeMapper;
import com.match.reviewresult.mapper.ExcellentPartmentMapper;
import com.match.reviewresult.mapper.ExcellentPersonMapper;
import com.match.reviewresult.mapper.ExcellentTeacherMapper;
import com.match.reviewresult.service.ExcellentPartmentService;

@Controller
@RequestMapping(value = "/Excle")
public class ExcleController {

	@Autowired
	private CompetitionAprizeService CompetitionAprizeService;
	
	
	@Autowired
	private  ExcellentPersonMapper  ExcellentPersonMapper;
	
	@Autowired
	private  ExcellentCollegeMapper  ExcellentCollegeMapper;
	
	@Autowired
	private  ExcellentPartmentService  ExcellentPartmentService;
	
	@Autowired
	private  ExcellentTeacherMapper  ExcellentTeacherMapper;
	
	
	/**
	 * 导出报表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/exportPrize.htm")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ObjectResult<List<Map<String, Object>>> orr = new ObjectResult<List<Map<String, Object>>>();

		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
//		if (getlogin != null) {
		String code = RequestParam.getString(request, "code");
		// 获取数据
		List<Map<String, Object>> list = CompetitionAprizeService.findHuoJiangThemeWork(code, getlogin.getDivisionid(), getlogin.getCompetitionid());

		// excel标题
		String[] title = {"序号","命题类别","命题名称","参赛编号","作品名称","作者","作者电话","作者email","作者QQ","老师","老师电话","老师email","学校","院系","提交时间","获奖等级"};

		String fileName = "获奖" + System.currentTimeMillis() + ".xls";

		String sheetName = "获奖";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = list.get(i);
			int k =1;
			String k1 = k+"";
			content[i][0] = k1;
			if(obj.get("typename")!=null)
			{
				content[i][1] = obj.get("typename").toString();
			}
			else
			{
				content[i][1] = "";
			}
			if(obj.get("themename")!=null)
			{
				content[i][2] = obj.get("themename").toString();
			}
			else
			{
				content[i][2] = "";
			}
			if(obj.get("stwcode")!=null)
			{
				content[i][3] = obj.get("stwcode").toString();
			}
			else
			{
				content[i][3] = "";
			}
			if(obj.get("title")!=null)
			{
				content[i][4] = obj.get("title").toString();
			}
			else
			{
				content[i][4] = "";
			}
			if(obj.get("realName")!=null)
			{
				content[i][5] = obj.get("realName").toString();
			}
			else
			{
				content[i][5] = "";
			}
			if(obj.get("phone")!=null)
			{
				content[i][6] = obj.get("phone").toString();
			}
			else
			{
				content[i][6] = "";
			}
			if(obj.get("email")!=null)
			{
				content[i][7] = obj.get("email").toString();
			}
			else
			{
				content[i][7] = "";
			}
			if(obj.get("qq")!=null)
			{
				content[i][8] = obj.get("qq").toString();
			}
			else
			{
				content[i][8] = "";
			}
			if(obj.get("teacherename")!=null)
			{
				content[i][9] = obj.get("teacherename").toString();
			}
			else
			{
				content[i][9] = "";
			}
			if(obj.get("teacherphone")!=null)
			{
				content[i][10] = obj.get("teacherphone").toString();
			}
			else
			{
				content[i][10] = "";
			}
			if(obj.get("teacheremail")!=null)
			{
				content[i][11] = obj.get("teacheremail").toString();
			}
			else
			{
				content[i][11] = "";
			}
			if(obj.get("collegename")!=null)
			{
				content[i][12] = obj.get("collegename").toString();
			}
			else
			{
				content[i][12] = "";
			}
			if(obj.get("dename")!=null)
			{
				content[i][13] = obj.get("dename").toString();
			}
			else
			{
				content[i][13] = "";
			}
			if(obj.get("ctime")!=null)
			{
				content[i][14] = obj.get("ctime").toString();
			}
			else
			{
				content[i][14] = "";
			}
			if(obj.get("prizename")!=null)
			{
				content[i][15] = obj.get("prizename").toString();
			}
			else
			{
				content[i][15] = "";
			}
			k=k+1;
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = Excle.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		} else {
//			orr.setCode(1);
//			orr.setErrmsg("尚未登录，无法获取数据");
//		}
//		String str = "";
//		str = Jacksonmethod.tojson(orr, false);
//		SessionMethod.writerespstr(response, str);
	}

	// 发送响应流方法
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * <p>功能描述：学生排名</p>
	 * <p>方法名：exportStudengScore</p>
	 * <p>@param request
	 * <p>@param response
	 * <p>@throws Exception</p>
	 * <p>返回类型：void</p>
	 * <p>创建日期：2019年6月29日 下午2:49:02</p>  
	 * <p>创建者：lxx</p>
	 */
	@RequestMapping(value = "/exportStudengScore.htm")
	@ResponseBody
	public void exportStudengScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		// 获取数据
		List<Map<String, Object>> list = ExcellentPersonMapper.findAllExcellentPerson(getlogin.getCompetitionid());

		// excel标题
		String[] title = {"序号","学生姓名","学校","院系","手机号","所获积分"};

		String fileName = "学生积分" + System.currentTimeMillis() + ".xls";

		String sheetName = "学生积分";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = list.get(i);
			int k =1;
			String k1 = k+"";
			content[i][0] = k1;
			if(obj.get("realName")!=null)
			{
				content[i][1] = obj.get("realName").toString();
			}
			else
			{
				content[i][1] = "";
			}
			if(obj.get("name")!=null)
			{
				content[i][2] = obj.get("name").toString();
			}
			else
			{
				content[i][2] = "";
			}
			if(obj.get("dename")!=null)
			{
				content[i][3] = obj.get("dename").toString();
			}
			else
			{
				content[i][3] = "";
			}
			if(obj.get("realName")!=null)
			{
				content[i][4] = obj.get("phone").toString();
			}
			else
			{
				content[i][4] = "";
			}
			if(obj.get("score")!=null)
			{
				content[i][5] = obj.get("score").toString();
			}
			else
			{
				content[i][5] = "";
			}
			k=k+1;
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = Excle.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 
	 * <p>功能描述：导出学校</p>
	 * <p>方法名：exportCollegeScore</p>
	 * <p>@param request
	 * <p>@param response
	 * <p>@throws Exception</p>
	 * <p>返回类型：void</p>
	 * <p>创建日期：2019年6月29日 下午3:13:53</p>  
	 * <p>创建者：lxx</p>
	 */
	@RequestMapping(value = "/exportCollegeScore.htm")
	@ResponseBody
	public void exportCollegeScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		// 获取数据
		List<Map<String, Object>> list = ExcellentCollegeMapper.findAllCollege(getlogin.getCompetitionid());

		// excel标题
		String[] title = {"序号","学校名称","所获积分"};

		String fileName = "学校积分" + System.currentTimeMillis() + ".xls";

		String sheetName = "学校积分";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = list.get(i);
			int k =1;
			String k1 = k+"";
			content[i][0] = k1;
			if(obj.get("name")!=null)
			{
				content[i][1] = obj.get("name").toString();
			}
			else
			{
				content[i][1] = "";
			}
			if(obj.get("score")!=null)
			{
				content[i][2] = obj.get("score").toString();
			}
			else
			{
				content[i][2] ="";
			}
			k=k+1;
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = Excle.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping(value = "/exportDepartmentScore.htm")
	@ResponseBody
	public void exportDepartmentScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		
		long collegeid = RequestParam.getLong(request, "collegeid");
		
		// 获取数据
		List<Map<String, Object>> list = ExcellentPartmentService.findAllExcellentPartment(getlogin.getCompetitionid(), collegeid);

		// excel标题
		String[] title = {"序号","学校名称","院系名称","所获积分"};

		String fileName = "学校院系积分" + System.currentTimeMillis() + ".xls";

		String sheetName = "学校院系积分";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = list.get(i);
			int k =1;
			String k1 = k+"";
			content[i][0] = k1;
			if(obj.get("name")!=null)
			{
				content[i][1] = obj.get("name").toString();
			}
			else
			{
				content[i][1] = "";
			}
			if(obj.get("dename")!=null)
			{
				content[i][2] = obj.get("dename").toString();
			}
			else
			{
				content[i][2] = "";
			}
			if(obj.get("score")!=null)
			{
				content[i][3] = obj.get("score").toString();
			}
			else
			{
				content[i][3] = "";
			}
			k=k+1;
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = Excle.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/exportTeacherScore.htm")
	@ResponseBody
	public void exportTeacherScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginMessage getlogin = SessionMethod.getlogin(request.getSession());
		
		long collegeid = RequestParam.getLong(request, "collegeid");
		
		// 获取数据
		List<Map<String, Object>> list = ExcellentTeacherMapper.findAllTeacherScore(getlogin.getCompetitionid(), collegeid);

		// excel标题
		String[] title = {"序号","老师名称","手机号","学校名称","职务","所获积分"};

		String fileName = "老师积分" + System.currentTimeMillis() + ".xls";

		String sheetName = "老师积分";

		String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> obj = list.get(i);
			int k =1;
			String k1 = k+"";
			content[i][0] = k1;
			if(obj.get("realName")!=null)
			{
				content[i][1] = obj.get("realName").toString();
			}
			else
			{
				content[i][1] ="";
			}
			if(obj.get("phone")!=null)
			{
				content[i][2] = obj.get("phone").toString();
			}
			else
			{
				content[i][2] ="";
			}
			if(obj.get("name")!=null)
			{
				content[i][3] = obj.get("name").toString();
			}
			else
			{
				content[i][3] ="";
			}
			if(obj.get("position")!=null)
			{
				content[i][4] = obj.get("position").toString();
			}
			else
			{
				content[i][4] ="";
			}
			if(obj.get("score")!=null)
			{
				content[i][5] = obj.get("score").toString();
			}
			else
			{
				content[i][5] ="";
			}
			k=k+1;
		}

		// 创建HSSFWorkbook
		HSSFWorkbook wb = Excle.getHSSFWorkbook(sheetName, title, content, null);

		// 响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
