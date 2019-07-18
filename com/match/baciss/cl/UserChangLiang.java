package com.match.baciss.cl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserChangLiang {

	//账号默认密码11
	public static String  password = "123456";
	
	//修改时间获取
	public  static  Date  mtime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return  sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static long  doubletolong(double s)
	{
		int s1 = (int)s;
		return (long)s1;
	}
	
	//企业负责人
	public  static  int  compant = 1;
	//学校负责人
	public  static  int  school = 2;
	//系统管理员
	public  static  int  sys = 3;
	//赛区负责人
	public  static  int  division = 4;
	//学生
	public  static  int  student = 5;
	//老师
	public  static  int  teacher = 6;
	//企业员工
	public  static  int  companystaff = 7;
	//其它
	public  static  int  others = 8;
}
