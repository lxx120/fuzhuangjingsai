package com.match.springutils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})  
@Documented  
@Retention(RetentionPolicy.RUNTIME) 
public @interface Authon {	 	
	public String module() default "操作";  
	public int operation() default 0;  
}
