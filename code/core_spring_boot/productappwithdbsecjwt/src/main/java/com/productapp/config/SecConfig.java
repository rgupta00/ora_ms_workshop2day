//package com.productapp.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.productapp.service.UserService;
//@Component
//@EnableWebSecurity(debug = true)
//public class SecConfig extends WebSecurityConfigurerAdapter{
//
//	//who are you -> 401: Authentation
//	@Autowired
//	private UserDetailsService detailsService;
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(detailsService);
//	}
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//		//return NoOpPasswordEncoder.getInstance();
//	}
//	
//	//I know who are u let me check if u can access this resouces -> 403
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		  http.csrf().disable()
//          .authorizeRequests()
//          .antMatchers("/admin/**").hasAnyRole("ADMIN")
//          .antMatchers("/mgr/**").hasAnyRole("ADMIN","MGR")
//          .antMatchers("/home/**").permitAll()
//          .and()
//          .httpBasic()
//          .and()
//          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
//
//}
//
//
//
////auth.inMemoryAuthentication()
////.withUser("raj").password("raj123").roles("ADMIN")
////.and()
////.withUser("ekta").password("ekta123").roles("MGR");
//
//
//
