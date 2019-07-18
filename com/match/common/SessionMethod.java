package com.match.common;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.match.baciss.model.LoginMessage;



/**
 * 封装了servlet相关接口
 * 
 * @author Administrator
 *
 */
public class SessionMethod {
	public static void writeresp(HttpServletResponse resp, String content) {
		if (resp == null || content == null)
			return;
		PrintWriter w = null;
		try {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json; charset=utf-8");
			w = resp.getWriter();
			w.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writerespstr(HttpServletResponse resp, String content) {
		if (resp == null || content == null)
			return;
		PrintWriter w = null;
		try {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html; charset=utf-8");
			w = resp.getWriter();
			w.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeImg(HttpServletResponse resp, String filepath) {
		if (resp == null || filepath == null)
			return;
		File f = new File(filepath);
		if (!f.exists())
			return;
		try {
			BufferedImage img = null;
			img = ImageIO.read(new FileInputStream(new File(filepath)));
			ImageIO.write(img, "jpeg", resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writerespstream(HttpServletResponse resp, String content) {
		if (resp == null || content == null)
			return;
		OutputStream os = null;
		try {
			resp.setCharacterEncoding("utf-8");
			os = resp.getOutputStream();
			os.write(content.getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void forward(ServletResponse resp, HttpServletRequest r, String url) {
		if (resp == null || r == null || url == null)
			return;
		try {
			HttpServletRequest hr = (HttpServletRequest) r;
			hr.getRequestDispatcher(url).forward(r, resp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(HttpServletRequest r,String name,Class<T> clazz){
		Object obj=r.getSession().getAttribute(name);
		
		if(obj==null){
			if(clazz==Integer.class){
				return (T)Integer.valueOf(0);
			}else if(clazz==Boolean.class){
				return (T)Boolean.valueOf(false);
			}else if(clazz==Long.class){
				return (T)Long.valueOf(0);
			}
			return null;
		}
		return (T)obj;
	}

	public static void sendRedirect(HttpServletResponse resp, HttpServletRequest r, String url) {
		if (resp == null || r == null || url == null)
			return;
		url = r.getServletContext().getContextPath() + url;
		try {
			resp.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String postBody(HttpServletRequest r) {
		if (r == null)
			return null;
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(r.getInputStream(), Charset.forName("utf-8")));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String download(HttpServletResponse resp,InputStream in,String displayName){
		try{
			resp.setContentType("application/x-download");//具体用什么到时候测试
			displayName=URLEncoder.encode(displayName,"utf-8");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+displayName+"\""); 
			BufferedInputStream bis=new BufferedInputStream(in);
			OutputStream out1=resp.getOutputStream();
			byte[] bytes=new byte[1024*4];
			int length=-1;
			while((length=bis.read(bytes))!=-1)
			{
				out1.write(bytes, 0, length);
			}
			out1.flush();
			bis.close();
			in.close();
			out1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String download(HttpServletResponse resp,String txt,String displayName){
		try{
			resp.setContentType("application/x-download");//具体用什么到时候测试
			displayName=URLEncoder.encode(displayName,"utf-8");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+displayName+"\""); 
			OutputStream out1=resp.getOutputStream();
			byte[] bytes=txt.getBytes("utf-8");
			int length=-1;
			out1.write(bytes, 0, length);
			out1.flush();
			out1.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String genDuplicationCode(HttpSession session){
		String uuid=UUID.randomUUID().toString();
		session.setAttribute(ConstantVar.session_duplication_code_name, uuid);
		return uuid;
	}
	
	public static void addActionLog(HttpServletRequest request,String osubject,boolean oresult){
		if(request==null)
			return;
		request.setAttribute(ConstantVar.action_log_request_osubject_name, osubject);
		request.setAttribute(ConstantVar.action_log_request_oresult_name, oresult);
	}
	
	public static ActionLog getActionLog(HttpServletRequest request,ActionLog log){
		log.setSubject(request.getAttribute(ConstantVar.action_log_request_osubject_name)+"");
		Object flg=request.getAttribute(ConstantVar.action_log_request_oresult_name);
		if(flg!=null){			
			log.setOresult((Boolean)flg);
		}
		return log;
	}
	
	public static LoginMessage getlogin(HttpSession session)
	{
		LoginMessage loginMessage=(LoginMessage)session.getAttribute(ConstantVar.sessionLoginName);
		return loginMessage;
	}
	
	public static boolean setlogin(HttpSession session,LoginMessage loginMessage)
	{
		session.setAttribute(ConstantVar.sessionLoginName, loginMessage);
		return true;
	}
}
