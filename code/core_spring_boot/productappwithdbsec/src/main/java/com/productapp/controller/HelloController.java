package com.productapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@GetMapping(path = "home")
	public String home() {
		return "home";
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping(path = "admin")
	public String admin() {
		return "admin";
	}
	
	@PreAuthorize("hasAuthority('ROLE_MGR') or hasAuthority('ROLE_ADMIN')")
	@GetMapping(path = "mgr")
	public String mgr() {
		return "mgr";
	}
	
}
