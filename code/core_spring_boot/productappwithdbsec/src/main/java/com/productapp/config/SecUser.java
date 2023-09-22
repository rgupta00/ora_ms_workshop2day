package com.productapp.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.productapp.entities.UserEntity;
//somehow i need to conver my user UserEntity to the user that spring sec understand

public class SecUser implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private UserEntity userEntity;
	
	public SecUser(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> namesList=userEntity.getRoles();
		
		String names[]=namesList.toArray(new String[namesList.size()]);	//AL to Array
		
		return AuthorityUtils.createAuthorityList(names);
	}

	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
