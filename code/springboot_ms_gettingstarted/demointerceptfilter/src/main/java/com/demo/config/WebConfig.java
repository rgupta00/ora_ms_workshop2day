package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptors.HelloInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private HelloInterceptor helloInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(helloInterceptor).addPathPatterns("/hello/**");
	}
	
	
}
