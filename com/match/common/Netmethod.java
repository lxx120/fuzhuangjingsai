package com.match.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class Netmethod {
	 public static InetAddress getlocaladdr(){
		InetAddress ia=null;
		if(Netmethod.iswindows())
		{
			try {
				ia=InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		if(ia!=null)
			return ia;
		try {
			Enumeration<NetworkInterface> ens = NetworkInterface.getNetworkInterfaces();
			boolean flg=false;
			while (ens.hasMoreElements()) {
				NetworkInterface nif = (NetworkInterface) ens.nextElement();
				UUID.randomUUID();
				Enumeration<InetAddress>  iiss=nif.getInetAddresses();
				System.out.println("mac地址:"+BasicMethod.byte2hexStr(nif.getHardwareAddress()));
				while(iiss.hasMoreElements())
				{
					InetAddress iis=iiss.nextElement();
					System.out.println("iis:"+iis.getHostAddress());
					if(!iis.isLoopbackAddress()&&iis.isSiteLocalAddress()&&iis.getHostAddress().indexOf(":")==-1)
					{
						System.out.println("找到inetaddr!!!");
						ia=iis;
						flg=true;
						break;
					}
				}
				if(flg==true)
					break;
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return ia;
	}
	
	 public static String allmac()
		{
			String re=null;
			Enumeration<NetworkInterface> nis;
			try {
				nis = NetworkInterface.getNetworkInterfaces();
				List<String> nds=new ArrayList<String>();
				while(nis.hasMoreElements())
				{
					NetworkInterface ni=nis.nextElement();
					byte[] bs=ni.getHardwareAddress();
					if(bs==null||bs.length!=6)
						continue;
					String str=BasicMethod.byte2hexStr(bs);
					if(str==null)
						continue;
					nds.add(str);
				}
				Collections.sort(nds);
				re=StringUtils.join(nds, "");
			} catch (SocketException e) {
				e.printStackTrace();
			}
			if(re==null)
				re=UUID.randomUUID().toString();
			return re;
		}
	 
	public static boolean iswindows()
	{
		 if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			  return true;
			  }
	   return false;	
	}
	
	private static String getcpunums()
	{
		String result = "";
		Path p=null;
		try {
			p=Files.createTempFile("tmp", ".vbs");
		    String vbs = "On Error Resume Next \r\n\r\n" + "strComputer = \".\"  \r\n"
		            + "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
		            + "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
		            + "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
		            + "For Each objItem in colItems\r\n " + "    Wscript.Echo objItem.ProcessorId  \r\n "
		            + "    exit for  ' do the first cpu only! \r\n" + "Next                    ";
		    System.out.println(p.toString());
		    	Files.write(p, vbs.getBytes("ISO-8859-1"));
		    	Process p1 = Runtime.getRuntime().exec("cscript //NoLogo " + p.toString());
		    	BufferedReader r=new BufferedReader(new InputStreamReader(p1.getInputStream()));
		    	String s=null;
		    	StringBuffer sb=new StringBuffer();
		    	while((s=r.readLine())!=null)
		    	{
		    		sb.append(s);
		    	}
		    	result=sb.toString();
		    	result=result.trim();
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally{
				if(p!=null)
					try {
						Files.deleteIfExists(p);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		   return result;
	}
	private static String getboardnums()
	{
		String result = "";
		Path p=null;
		try {
			p=Files.createTempFile("tmp2", ".vbs");
		    String vbs ="On Error Resume Next \r\n\r\n" + "strComputer = \".\"  \r\n"
            + "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
            + "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
            + "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_BaseBoard\")  \r\n "
            + "For Each objItem in colItems\r\n " + "    Wscript.Echo objItem.SerialNumber  \r\n "
            + "    exit for  ' do the first cpu only! \r\n" + "Next                    ";
		    System.out.println(p.toString());
		    	Files.write(p, vbs.getBytes("ISO-8859-1"));
		    	Process p1 = Runtime.getRuntime().exec("cscript //NoLogo " + p.toString());
		    	BufferedReader r=new BufferedReader(new InputStreamReader(p1.getInputStream()));
		    	String s=null;
		    	StringBuffer sb=new StringBuffer();
		    	while((s=r.readLine())!=null)
		    	{
		    		sb.append(s);
		    	}
		    	result=sb.toString();
		    	result=result.trim();
		    }catch (Exception e) {
		    	e.printStackTrace();
			}finally{
				if(p!=null)
					try {
						Files.deleteIfExists(p);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		   return result;
	}
	
	private static String getmac()
	{
		String str=null;
		try {
			InetAddress ia=getlocaladdr();
			NetworkInterface nif=NetworkInterface.getByInetAddress(ia);
			byte[] bytes=nif.getHardwareAddress();
			str=BasicMethod.byte2hexStr(bytes);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getserial()
	{
		if(iswindows())
		{
			String str=null;
			str=getboardnums();
			if(str!=null&&!str.isEmpty())
			{
				System.out.println("主板序列号:"+str);
				return str;
			}
			str=getcpunums();
			System.out.println("cpu num:"+str);
			if(str==null||str.isEmpty())
			{
				str=getmac();
			}
			return str;
		}else
		{
			System.out.println("非windows，返回mac");
			return getmac();
		}
	}
}
