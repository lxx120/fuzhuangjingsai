package com.match.common;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.DefaultCookieSpecProvider;
import org.apache.http.impl.cookie.RFC6265CookieSpecProvider;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/** 
 * @author mengly 
 * @version 创建时间：2015年9月18日 下午7:53:25 
 * 类说明 :包依赖:httpclient,之所以采用singleton，是因为有hcontext
 */
@Service
public class Httpmethod {
	private HttpClientContext hcontext=null;
	private CloseableHttpClient ht=null;
	private CookieStore cookiestore=null;
	public CloseableHttpClient ssl() {
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
	/**
	 * new ssl();
	 * @return 
	 * @author mengly 
	 * @version 创建时间：2015年10月13日 上午11:21:15
	 */
	public CloseableHttpClient nssl() {
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
	public String post(String url,Map<String, String> params)
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
			 if(hcontext!=null)
				cr= httpclient.execute(hp, hcontext);
			 else
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
	
	public String ssl_post(String url,Map<String, String> params)
	{
		 CloseableHttpClient httpclient=nssl();
		// CloseableHttpClient httpclient = HttpClients.createDefault();
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
			 if(hcontext==null)
				 cr=httpclient.execute(hp);
			 else
				 cr=httpclient.execute(hp, hcontext);
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
	
	public String ssl_postbody(String url,String body)
	{
		 CloseableHttpClient httpclient=nssl();
		 HttpPost hp=new HttpPost(url);
		 StringEntity se=new StringEntity(body, Charset.forName("utf-8"));
		 hp.setEntity(se);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 if(hcontext==null)
				 cr=httpclient.execute(hp);
			 else
				 cr=httpclient.execute(hp, hcontext);
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
	
	
	public String get(String url)
	{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 if(hcontext==null)
				 cr=httpclient.execute(hp);
			 else
				 cr=httpclient.execute(hp, hcontext);
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
	public String ssl_get(String url)
	{
		 CloseableHttpClient httpclient =nssl();
		 HttpGet  hp=new HttpGet(url);
		 String re=null;
		 try {
			 CloseableHttpResponse cr=null;
			 if(hcontext==null)
				 cr=httpclient.execute(hp);
			 else
				 cr=httpclient.execute(hp, hcontext);
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
	public String upload(String url,String file)
	{
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpPost hp=new HttpPost(url);
		 String re=null;
		 FileBody fb=new FileBody(new File(file));
		 StringBody comment=new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
		 HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", fb).addPart("comment", comment).build();
		 try {
			 hp.setEntity(reqEntity);
			 CloseableHttpResponse cr=	httpclient.execute(hp);
			 try{
				 HttpEntity he=cr.getEntity();
				 if(he!=null)
					 re=EntityUtils.toString(he,"UTF-8");
				 EntityUtils.consume(he); 
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
	public boolean clearcontext()
	{
		this.cookiestore.clear();
		this.cookiestore=null;
		this.hcontext=null;
		System.gc();
		return true;
	}
	public boolean createcontext()
	{
		hcontext = HttpClientContext.create();
		cookiestore=new BasicCookieStore();
		PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.getDefault();
	    Registry<CookieSpecProvider> registry = RegistryBuilder
	        .<CookieSpecProvider> create()
	        .register(CookieSpecs.DEFAULT, new DefaultCookieSpecProvider(publicSuffixMatcher))
	        .register(CookieSpecs.STANDARD,
	            new RFC6265CookieSpecProvider(publicSuffixMatcher)).build();
	    hcontext.setCookieSpecRegistry(registry);
	    hcontext.setCookieStore(cookiestore);
		return true;
	}
	
	public boolean addcookie(String name,String value)
	{
		if(this.hcontext==null||this.cookiestore==null)
			return false;
		BasicClientCookie bc=new BasicClientCookie(name, value);
		this.cookiestore.addCookie(bc);
		return true;
	}
	public boolean clearcooike()
	{
		if(this.hcontext==null||this.cookiestore==null)
			return false;
		cookiestore.clear();
		return true;
	}
	
	public void close(){
		if(this.ht!=null){
			try {
				this.ht.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
