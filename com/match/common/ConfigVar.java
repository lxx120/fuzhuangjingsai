package com.match.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigVar {
	public static String host_base="http://wap.pianyipi.com";
	static{
		InputStream is=ConstantVar.class.getResourceAsStream("/configvar.properties");
		Properties p=new Properties();
		try {
			p.load(is);
			host_base=p.getProperty("host_base");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
