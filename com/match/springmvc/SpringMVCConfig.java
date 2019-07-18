package com.match.springmvc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.match.springmvc.interceptors.AuthonInterceptor;
import com.match.springmvc.interceptors.DuplicationSubmitInterceptor;
//import com.match.springmvc.interceptors.LogInterceptor;
import com.match.springmvc.interceptors.LoginInterceptor;
import com.match.springmvc.interceptors.WechatLoginInterceptor;

//1./**/*.htm  :    只拦截固定的后缀              注意：**.htm    /**.htm   *.htm 的方式是错的
//2./**           ：   指所有文件夹及里面的子文件夹
//3./*            ：   指所有文件夹，不含子文件夹
//4./             ：   指web项目的根目录
/**
 * 
 * @author mengly
 *
 */
@Configuration
@EnableWebMvc
public class SpringMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		super.configureHandlerExceptionResolvers(exceptionResolvers);
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		smer.setDefaultErrorView("/errors/mvcerror");
		smer.setExceptionAttribute("mvcexception");
		exceptionResolvers.add(smer);
	}

	@Bean
	WechatLoginInterceptor getWechatLoginInterceptor(){
		return new WechatLoginInterceptor();
	}

	@Bean
	LoginInterceptor getLoginInterceptor() {
		return new LoginInterceptor();
	}
	
//	LogInterceptor getLogInterceptor(){
//		return new LogInterceptor();
//	}

	@Bean
	AuthonInterceptor getAuthonInterceptor() {
		return new AuthonInterceptor();
	}
	
	@Bean
	DuplicationSubmitInterceptor getDuplicationSubmit() {
		return new DuplicationSubmitInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(getWechatLoginInterceptor())
				.addPathPatterns("/merchant/wechat/index.htm");
		registry.addInterceptor(getLoginInterceptor())
				.addPathPatterns("/**/*.htm ")
				.excludePathPatterns("/merchant/wechat/receiver.htm")
				.excludePathPatterns("/merchant/weixin/anonymous/wholesaler/*/goods/begin.htm")
				.excludePathPatterns("/merchant/weixin/anonymous/wholesalers/*/goods.htm")
				.excludePathPatterns("/merchant/weixin/anonymous/wholesaler/*/goods/*/view.htm")
				.excludePathPatterns("/merchant/weixin/anonymous/wholesalers/*/goodstypes.htm")
				.excludePathPatterns("/merchant/weixin/anonymous/wholesalers/*/wholesalerConfig.htm")
				.excludePathPatterns("/merchant/wechat/authen/callback.htm")
				.excludePathPatterns("/merchant/wechat/index.htm")
				.excludePathPatterns("/merchant/weixin/phone/getcode.htm")
				.excludePathPatterns("/merchant/weixin/phone/checkRegister.htm")
				.excludePathPatterns("/allurls/urls.htm")
				.excludePathPatterns("/**/*login.htm")
				.excludePathPatterns("/**/*protocol.htm")
				.excludePathPatterns("/**/*register.htm")
				.excludePathPatterns("/**/district/**.htm")
				.excludePathPatterns("/**/errors**.htm")
				.excludePathPatterns("/merchant/weixin/barcode/view/*.htm")
				.excludePathPatterns("/merchant/weixin/barcode/scan/*.htm")
				.excludePathPatterns("/**/password/retrieve/*.htm");
		
//		registry.addInterceptor(getAuthonInterceptor())
//				.addPathPatterns("/**/*.htm ")
//				.excludePathPatterns("/merchant/weixin/phone/getcode.htm")
//				.excludePathPatterns("/**/*login.htm")
//				.excludePathPatterns("/**/*protocol.htm")
//				.excludePathPatterns("/**/*register.htm")
//				.excludePathPatterns("/**/district/**.htm")
//				.excludePathPatterns("/**/errors**.htm")
//				.excludePathPatterns("/merchant/weixin/barcode/view**.htm")
//				.excludePathPatterns("/merchant/weixin/barcode/scan**.htm")
//				.excludePathPatterns("/**/password/retrieve/*.htm");
		registry.addInterceptor(getDuplicationSubmit())
				.excludePathPatterns("/merchant/wechat/**.htm")
				.addPathPatterns("/**/*.htm ");
		
		
	}

	
//	@Bean
//	RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
//		return new RequestMappingHandlerAdapter();
//	}
//
//	@Bean
//	RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//		return new RequestMappingHandlerMapping();
//	}
}
