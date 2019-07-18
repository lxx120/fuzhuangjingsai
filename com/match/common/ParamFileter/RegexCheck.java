
package com.match.common.ParamFileter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.match.common.result.ObjectResult;




/** 
 * 正则表达式验证 <br/> 
 * 2016年4月10日 下午4:37:21 <br/> 
 * @author   guolq        
 */
public class RegexCheck {
	
	
	/** 
	 * checkphone:验证手机号码11位
	 * @param phone
	 * @return 
	 * TODO
	 */  
	public static boolean checkphone(String phone){
		boolean flag = false; 
		try{ 
			Pattern p = Pattern.compile("^[1][0-9]{10}$"); //^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$
			Matcher m = p.matcher(phone);
			flag = m.matches(); 
		}catch(Exception e){ 
			flag = false; 
		} 
			return flag;
	}
	
	/** 
	 * checkempty:字符串不能为空和null
	 * @param strargs
	 * @return 
	 * TODO
	 */  
	public static boolean checkempty(String...strargs){
		for (String str : strargs) {
			if (str==null||str.trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * checkpassword:验证密码某些格式
	 * @param pwd
	 * @param oldpwd
	 * @return 
	 * TODO
	 */  
	public static ObjectResult<Boolean> checkpassword(String pwd,String oldpwd){
		ObjectResult<Boolean> objresult =new ObjectResult<Boolean>();
		try {
			if(oldpwd==null||pwd==null||oldpwd.trim().isEmpty()||pwd.trim().isEmpty()){
				objresult.setCode(1);
				objresult.setHint("密码不能设置为空");
				objresult.setItem(false);
				return objresult;
			}
			//pwd=pwd.replaceAll("\\s*|\t|\r|\n|　|	|", "").replaceAll("　| ", "");//去除字符串中所包含的空格（包括:空格（半角）、制表符、换页符等） 
			Pattern p = Pattern.compile("(?!^\\d+$)(?!^[a-zA-Z]+$).{6,16}"); 
			Matcher m = p.matcher(pwd);
			boolean boo = m.matches(); 
			if(boo){
				objresult.setCode(0);
				objresult.setHint(pwd);
				objresult.setItem(boo);
			}else{
				objresult.setCode(1);
				objresult.setHint("密码格式必须包含：字母和数字(可以包含特殊字符)的6到16位的字符串");
				objresult.setItem(boo);
			}
		} catch (Exception e) {
			objresult.setCode(1);
			objresult.setHint("密码检验出错，请重新输入密码！！");
		}
		return objresult;	
	}
	
	
	/** 
	 * getsearchwords:简单处理分割一些搜索词
	 * @param shword
	 * @return 
	 * TODO
	 */  
	public static String getsearchwords(String shword){
		
		if (shword==null||shword.trim().isEmpty()) 
			return null;
		if (shword.length()>200) {
			shword=shword.substring(0, 200);//200个字符
		}
		
		shword=shword.replaceAll("　|,|，|，|	|	", " ");//替换一些特殊字符
		shword=shword.replaceAll("&nbsp{2,}", " ");
		String[] strs=shword.trim().split(" ");
		if (strs==null) 
			return null;
		StringBuffer strbuf=new StringBuffer();
		for (int i =0; i<strs.length; i++) {
			if (strs[i].trim().isEmpty()) 
				continue;
			strbuf.append(strs[i]+",");
		}
		return strbuf.toString();
		
	}
	
	public static boolean isIP(String ipaddress){
			String ip = "((([1-9]?|1\\d)\\d|2([0-4]\\d|5[0-5]))\\.){3}(([1-9]?|1\\d)\\d|2([0-4]\\d|5[0-5]))";   
	       Pattern pattern = Pattern.compile(ip);   
	       Matcher matcher = pattern.matcher(ipaddress);   
	       return matcher.matches();   
	}
	
	/** 
	 * getRadm:生成任意位数的随机字符串
	 * @param panflag
	 * @param randomnum
	 * @return 
	 * TODO
	 */  
	public static String  getRadm(int panflag,int randomnum){
		char[] chars =new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		StringBuffer result =new StringBuffer();
		if(panflag==1){
			for (int i = 0; i < randomnum ; i++) {
				int indx=(int)(Math.random()*340);
				indx=indx%34;
				result.append(chars[indx]);
			}
		}else if(panflag==2){
			for (int i = 0; i < randomnum ; i++) {
				int indx=(int)(Math.random()*580);
				indx=indx%58;
				result.append(chars[indx]);
			}
		}else if(panflag==3){
			for (int i = 0; i < randomnum ; i++) {
				int indx=(int)(Math.random()*100);
				indx=indx%10;
				result.append(chars[indx]);
			}
		}
		return result.toString();
	}
	
	/** 
	 * repllusername:字符串替换姓名 匿名
	 * @param username
	 * @return 
	 * TODO
	 */  
	public static String repllusername(String username){
		if (username==null||username.trim().isEmpty()) {
			return "y***"+9;
		}
		if (username.length()<=1) {
			return username+"***"+9;
		}
		if (username.length()==2) {
			return username.substring(0,1)+"***"+username.substring(1);
		}
		return username.substring(0,1)+"***"+username.substring(username.length()-1);
	}
	
}
