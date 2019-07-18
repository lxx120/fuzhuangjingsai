package com.match.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;



@SuppressWarnings("all")
public class Zhuanbianduixiang {




		/**
		 *
		 * 从request中取出请求参数，把这些参数对应反射为JavaBean对象
		 * 
		 * @param request
		 * @param cls
		 * @return
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 * @throws UnsupportedEncodingException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 */
		public <T> T reflects(HttpServletRequest request, Class<T> cls) throws InstantiationException, IllegalAccessException, UnsupportedEncodingException, IllegalArgumentException, InvocationTargetException{
			request.setCharacterEncoding("utf-8");//设置请求(request)的编码集
			T t=cls.newInstance();
			Map<String, String[]> parameterMap=request.getParameterMap();//获取所有请求参数
			List<Method> objectSetMethods=getObjectSetMethods(cls);//获取JavaBean中所有的set方法
			String key="";
			String value="";
			for(Map.Entry<String,String[]> parameter:parameterMap.entrySet()){			
				key="set"+parameter.getKey();
				String values[]=parameter.getValue();
				if(values!=null && values.length>0){
					value=values[0];
				}
				for(Method method:objectSetMethods){
					if(key.equalsIgnoreCase(method.getName())){
						Class c=method.getParameterTypes()[0];
						String parameterType=c.getTypeName();
						if(!"".equals(value)){
							if (!"".equals(value)) {
								if ("int".equals(parameterType) || "java.lang.Integer".equals(parameterType)) {
									int v = Integer.parseInt(value);
									method.invoke(t, v);
								} else if ("float".equals(parameterType) || "java.lang.Float".equals(parameterType)) {
									float v = Float.parseFloat(value);
									method.invoke(t, v);
								} else if ("double".equals(parameterType) || "java.lang.Double".equals(parameterType)) {
									double v = Double.parseDouble(value);
									method.invoke(t, v);
								} else if("short".equals(parameterType) || "java.lang.Short".equals(parameterType)){
									short v = Short.parseShort(value);
									method.invoke(t, v);
								} else if("long".equals(parameterType) || "java.lang.Long".equals(parameterType)){
									long v = Long.parseLong(value);
									method.invoke(t, v);
								}  else {
									method.invoke(t, value);
								}
							}
						}
					}
				}
			}		
			return t;
		}

		/**
		 * 
		 * 获取对象(JavaBean)的全部set方法
		 * 
		 * @param cls 对象(JavaBean)
		 * @return List<Method>
		 */
		private <T> List<Method> getObjectSetMethods(Class<T> cls){
			List<Method> setMethods = new ArrayList<Method>();
			Method[] methods = cls.getMethods();
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					setMethods.add(method);
				}
			}
			return setMethods;
		}

		 /** 
	     *  
	     *  
	     * Map转换层Bean，使用泛型免去了类型转换的麻烦。 
	     * @param <T> 
	     * @param map   
	     * @param class1 
	     * @return 
	     */
		 public static <T> T map2Bean(Map<String, Object> map, Class<T> class1) {  
		        T bean = null;  
		        try {  
		            bean = class1.newInstance();  
		            BeanUtils.populate(bean, map);  
		        } catch (InstantiationException e) {  
		            e.printStackTrace();  
		        } catch (IllegalAccessException e) {  
		            e.printStackTrace();  
		        } catch (InvocationTargetException e) {  
		            e.printStackTrace();  
		        }  
		        return bean;  
		    } 
		 
}
