package com.match.common;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class BasicMethod {
	public static String byte2hexStr(byte[] buf){
		if(buf==null)
			return null;
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<buf.length;i++){
			String hex=Integer.toHexString(buf[i]&0xff);
			if(hex.length()==1)
			{
				hex='0'+hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	public static byte[] hexStr2bytes(String str){
		if(str==null||str.length()<1)
			return null;
		byte[] result=new byte[str.length()>>1];
		for(int i=0;i<str.length();i+=2){
			int high=Integer.parseInt(str.substring(i, i+1),16);
			int low=Integer.parseInt(str.substring(i+1, i+2),16);
			result[i>>1]=(byte)(((high<<4)&0xf0)+low);
		}
		return result;
	}
	/**
	 * Aes 加密
	 * @param source
	 * @param password
	 * @return
	 */
	public static String encryptAES(String source,String password){
		if(source==null||password==null)
			return null;
		try {
			KeyGenerator keygen=KeyGenerator.getInstance("AES");
			SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(password.getBytes());
			keygen.init(128,sr);
			SecretKey key=keygen.generateKey();
			byte[] keyendoce=key.getEncoded();
			SecretKeySpec keyspec=new SecretKeySpec(keyendoce, "AES");
			Cipher cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE,keyspec );
			byte[] sbytes=null;
			try {
				sbytes = cipher.doFinal(source.getBytes("utf8"));
			} catch (UnsupportedEncodingException e) {
				System.out.println("加密过程中，getbyte有错误!");
				e.printStackTrace();
			}
			return byte2hexStr(sbytes);  //这里也可以用base64来表示,这样解密的时候反解析base64
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	public static String decryptAES(String source,String password)
	{
		if(source==null||password==null)
			return "";
		byte[] sbytes=hexStr2bytes(source);
		try {
			KeyGenerator keygen=KeyGenerator.getInstance("AES");
			SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed(password.getBytes());
			keygen.init(128,sr);
			SecretKey key=keygen.generateKey();
			byte[] keyendoce=key.getEncoded();
			SecretKeySpec keyspec=new SecretKeySpec(keyendoce, "AES");
			Cipher cipher=Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, keyspec);
			byte[] dbytes=cipher.doFinal(sbytes);
			try {
				return new String(dbytes,"utf8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("解密过程中，编码错误!");
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String encryptSHA(String source)//SHA 摘要，不可逆
	{
		if(source==null)
			return null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] bytes=md.digest(source.getBytes());
			return byte2hexStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String encryptMD5(String source)//SHA 摘要，不可逆
	{
		if(source==null)
			return null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] bytes=md.digest(source.getBytes());
			return byte2hexStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String encryptSHA1(String source)//SHA 摘要，不可逆
	{
		if(source==null)
			return null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] bytes=md.digest(source.getBytes());
			return byte2hexStr(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static boolean checkEmailFormat(String email)
	{
		if(email==null)
			return false;
		String patterns="[a-zA-Z]+([-.][a-zA-Z0-9_-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +"(\\.[A-Za-z]{2,3})$";
		Pattern p=Pattern.compile(patterns);
		Matcher m=p.matcher(email);
		if(m.matches())
			return true;
		return false;
	}
	public static boolean checkvalidusername(String username)
	{
		if(username==null)
			return false;
		String patterns="^[a-zA-Z][a-zA-Z0-9._-]{5,50}";
		Pattern p=Pattern.compile(patterns);
		Matcher m=p.matcher(username);
		if(m.matches())
			return true;
		return false;
	}
	public static boolean checkIP(String ip)
	{
		if(ip==null)
			return false;
		String pattern="^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(ip);
		if(m.matches())
			return true;
		return false;
	}
	/**
	 * 判断date是否处于当前时间-days前
	 * @param date
	 * @param days
	 * @return 
	 */
	public static boolean before(Date date, int days)
	{
		if(date==null)
			return false;
		Date now=new Date(System.currentTimeMillis());
		Calendar nowcal=Calendar.getInstance();
		nowcal.setTime(now);
		Calendar datecal=Calendar.getInstance();
		datecal.setTime(date);
		nowcal.add(Calendar.DAY_OF_MONTH, -days);
		if(datecal.before(nowcal))
			return true;
		return false;
	}
	/**
	 * 判断date是否处于当前时间+days后
	 * @param date
	 * @param days
	 * @return
	 */
	public static boolean after(Date date,int days)
	{
		if(date==null)
			return false;
		Date now=new Date(System.currentTimeMillis());
		Calendar nowcal=Calendar.getInstance();
		nowcal.setTime(now);
		Calendar datecal=Calendar.getInstance();
		datecal.setTime(date);
		nowcal.add(Calendar.DAY_OF_MONTH, days);
		if(datecal.after(nowcal))
			return true;
		return false;
	}
	/**
	 * 讲string字符串，规整化掉sql里面的特殊字符'和\
	 */
	public static String sqlformat(String param)
	{
		if(param==null)
			return null;
		param=param.trim();
		String t=param.replaceAll("'", "\\\\'");
		t=t.replaceAll("\"", "\\\\\"");
		return t;
	}
	/**
	 * 使用Jsoup包解析html content，并提取出所有的img超链接
	 * @param content
	 * @return
	 */
	public static String parseimgs(String content)
	{
		if(content==null)
			return null;
		Document doc=Jsoup.parse(content);
		Elements eles=doc.select("img");
		Iterator<Element> ele=eles.iterator();
		Set<String> set=new HashSet<String>();
	    while(ele.hasNext())
	    {
	    	Element e=ele.next();
	    	set.add(e.attr("src"));
	    }
	    if(set.size()>0)
	    	return Jacksonmethod.tojson(set, false);
	    return null;
	}
	public static String randomstr(int length)
	{
		if(length<=0)
			return null;
		 char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
		 Random r=new Random(System.currentTimeMillis());
		 int charlength=chars.length;
		 StringBuffer sb=new StringBuffer();
		 for (int i = 0; i <length; i++) {
			int ri=r.nextInt(charlength);
			sb.append(chars[ri]);
		}
		return sb.toString();
	}
	
	public static String first2Upper(String str)
	{
		if(str==null)
			return null;
		char[] cs=str.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}
	
	/** 
     * 获取本机的IP 
     * @return Ip地址 
     */ 
     public static String getLocalHostIP() { 
          String ip; 
          try { 
               /**返回本地主机。*/ 
               InetAddress addr = InetAddress.getLocalHost(); 
               /**返回 IP 地址字符串（以文本表现形式）*/ 
               ip = addr.getHostAddress();  
          } catch(Exception ex) { 
              ip = ""; 
          } 
            
          return ip; 
     } 
     
     public static String md5Strs(String[] params){
    	 Arrays.sort(params);
    	 String str=StringUtils.join(params,"");
    	 return  encryptMD5(str);
     }
       
}
