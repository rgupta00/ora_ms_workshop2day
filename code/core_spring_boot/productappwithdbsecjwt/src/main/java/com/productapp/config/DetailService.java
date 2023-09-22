package com.productapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productapp.entities.UserEntity;
import com.productapp.service.UserService;
@Service
public class DetailService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserEntity userEntity=userService.findByUsername(username);
		if(userEntity==null)
			throw new UsernameNotFoundException("user not found");
		
		//somehow i need to conver my user UserEntity to the user that spring sec understand
		return new SecUser(userEntity);
	}

}
