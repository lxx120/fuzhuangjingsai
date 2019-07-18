package com.match.weixin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;


/** 
 * @author mengly 
 * @version 创建时间：2015年10月27日 上午9:59:53 
 * 类说明 
 */

public class Wxhtmethod {
	/**
	 * new ssl();
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2015年10月13日 上午11:21:15
	 */
	public static CloseableHttpClient nssl() {
        CloseableHttpClient httpclient = null;  
        try {  
            SSLContext sslcontext = SSLContexts.createDefault();  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);  
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
        }catch(Exception ee){
        	ee.printStackTrace();
        }
        return httpclient;
    } 
	public static String get(String url)
	{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 cr=httpclient.execute(hp);
			 try{
				
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
				 
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	/**
	 * 下载图片
	 * @param url
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2016年4月8日 下午7:59:04
	 */
	public static String getBody2Image(String url,String filepath)
	{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			cr=httpclient.execute(hp);
			 try{
				 InputStream in=cr.getEntity().getContent();
				 BufferedImage bi=ImageIO.read(in);
				 if(bi!=null)
				 {
					 ImageIO.write(bi, "jpg", new File(filepath));
				 }
				 return filepath;
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	public static String ssl_get(String url)
	{
		 CloseableHttpClient httpclient =nssl();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 cr=httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	public static String ssl_getBody2Image(String url,String filepath)
	{
		 CloseableHttpClient httpclient =nssl();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			CloseableHttpResponse cr=null;
			cr=httpclient.execute(hp);
			 try{
				 InputStream in=cr.getEntity().getContent();
				 BufferedImage bi=ImageIO.read(in);
				 File f=new File(filepath);
				 if(bi!=null)
				 {
					 ImageIO.write(bi, "jpg", new File(filepath));
				 }
				 return f.getPath();
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	public static BufferedImage ssl_getBody2BfdImage(String url)
	{
		 CloseableHttpClient httpclient =nssl();
		 HttpGet  hp=new HttpGet(url);
		 try {
			CloseableHttpResponse cr=null;
			cr=httpclient.execute(hp);
			 try{
				 InputStream in=cr.getEntity().getContent();
				 BufferedImage bi=ImageIO.read(in);
				 return bi;
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return null;
	}
	
	
	public static String post(String url,Map<String, String> params)
	{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpPost hp=new HttpPost(url);
		 String re=null;
		 List<NameValuePair> fps=new ArrayList<NameValuePair>();
		 UrlEncodedFormEntity entity=null;
		 if(params!=null)
		 {
			 for (Entry<String, String> en : params.entrySet()) {
				 fps.add(new BasicNameValuePair(en.getKey(), en.getValue()));
			 }
		 }
		 try {
			 entity=new UrlEncodedFormEntity(fps,"UTF-8");
			 hp.setEntity(entity);
			 CloseableHttpResponse cr=null;
			 cr= httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	
	public static String ssl_post(String url,Map<String, String> params)
	{
		 CloseableHttpClient httpclient=nssl();
		 HttpPost hp=new HttpPost(url);
		 String re=null;
		 List<NameValuePair> fps=new ArrayList<NameValuePair>();
		 UrlEncodedFormEntity entity=null;
		 if(params!=null)
		 {
			 for (Entry<String, String> en : params.entrySet()) {
				 fps.add(new BasicNameValuePair(en.getKey(), en.getValue()));
			 }
		 }
		 try {
			 entity=new UrlEncodedFormEntity(fps,"UTF-8");
			 hp.setEntity(entity);
			 CloseableHttpResponse cr=null;
			 cr=httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	
	public static String ssl_postmultipart(String url,Map<String, String> params,String upfilename,File file)
	{
		 CloseableHttpClient httpclient=nssl();
		 HttpPost hp=new HttpPost(url);
		 MultipartEntityBuilder me=MultipartEntityBuilder.create();
		 me.addBinaryBody(upfilename, file);
		 if(params!=null)
			 for (Entry<String, String> en : params.entrySet()) {
				 me.addTextBody(en.getKey(), en.getValue());
			 }
		 hp.setEntity(me.build());
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 cr=httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	public static String ssl_postbody(String url,String body)
	{
		 CloseableHttpClient httpclient=nssl();
		 HttpPost hp=new HttpPost(url);
		 StringEntity se=new StringEntity(body, Charset.forName("utf-8"));
		 hp.setEntity(se);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 cr=httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
			 }catch(Exception ee)
			 {}finally{
				 cr.close();
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } 
		}
		return re;
	}
	public static String first2Upper(String str)
	{
		if(str==null)
			return null;
		char[] cs=str.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}
	
}
