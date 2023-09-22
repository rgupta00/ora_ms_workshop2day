package com.productapp.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

@Service
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;
		
		 if(authHeader!=null && authHeader.startsWith("Bearer ")){
	            token=authHeader.substring(7);
	            username=jwtService.extractUsername(token);
	       }
		 //if username is not null and he is not currently logged in
		 
		  if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
			  //get the userdetails
	            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
	            //username is correct , and we are going to get UNAuthToeken and put that in SecurityContextHolder ....
	          //validate the token
	            if(jwtService.validateToken(token, userDetails)){

	                UsernamePasswordAuthenticationToken authToken=
	                     new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	               // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                
	                //as user is valid delebratly add the user details in securitycontext
	                SecurityContextHolder.getContext().setAuthentication(authToken);

	            }

	        }
	        filterChain.doFilter(request, response);
	}

}














