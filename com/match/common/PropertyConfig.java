package com.match.common;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfig extends PropertyPlaceholderConfigurer{
	private static final String key = ConstantVar.JDBC_DESC_KEY;
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)throws BeansException {
        try {
            String username = props.getProperty(ConstantVar.JDBC_DATASOURCE_USERNAME_KEY);
            if (username != null) {
                props.setProperty(ConstantVar.JDBC_DATASOURCE_USERNAME_KEY, BasicMethod.decryptAES(username, key));
            }
            String password = props.getProperty(ConstantVar.JDBC_DATASOURCE_PASSWORD_KEY);
            if (password != null) {
                props.setProperty(ConstantVar.JDBC_DATASOURCE_PASSWORD_KEY, BasicMethod.decryptAES(password, key));
            }
            
            String url = props.getProperty(ConstantVar.JDBC_DATASOURCE_URL_KEY);
            if (url != null) {
                props.setProperty(ConstantVar.JDBC_DATASOURCE_URL_KEY, BasicMethod.decryptAES(url, key));
            }
            
            String driverClassName = props.getProperty(ConstantVar.JDBC_DATASOURCE_DRIVERCLASSNAME_KEY);
            if(driverClassName != null){
                props.setProperty(ConstantVar.JDBC_DATASOURCE_DRIVERCLASSNAME_KEY, BasicMethod.decryptAES(driverClassName, key));
            }
            super.processProperties(beanFactory, props);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanInitializationException(e.getMessage());
        }
	 }
}
