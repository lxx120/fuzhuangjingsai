package com.match.springmvc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.match.springmvc.penum.OperationEnum;

@Target({ElementType.METHOD})  
@Documented  
@Retention(RetentionPolicy.RUNTIME) 
public @interface PermissionAnno {
	
	public String module();
	
	public String dependencyModule() default "无";
	
	public String authorObject();
	
	public OperationEnum authorOperation();
		
}
