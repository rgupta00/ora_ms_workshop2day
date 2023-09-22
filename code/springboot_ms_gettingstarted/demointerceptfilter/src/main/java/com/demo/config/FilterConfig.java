package com.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.controller.HelloController;
import com.demo.filters.HelloFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<HelloFilter>filterRegistrationBean(){
		FilterRegistrationBean<HelloFilter> bean=new FilterRegistrationBean<HelloFilter>();
		bean.setFilter(new HelloFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}
}
