package com.match.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
import java.security.cert.Certificate;

/** 
 * @author mengly 
 * @version 创建时间：2017年1月5日 下午7:19:16 
 * 类说明 
 */

public class Custom509TrustManager implements X509TrustManager {
	private Certificate cert = null;
	public Custom509TrustManager(File keyfile) throws Exception {
	        // create a "default" JSSE X509TrustManager.
		try{
			FileInputStream fis = new FileInputStream(keyfile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			while (bis.available() > 0) {
				cert = cf.generateCertificate(bis);
			}
            bis.close();
		}catch(Exception e){
			
		}
			
	}
	
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		for (X509Certificate cert : chain) {
			if (cert.toString().equals(this.cert.toString()))
				return;
		}
		throw new CertificateException("certificate is illegal");
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[] { (X509Certificate) cert };
	}

}
