package com.productapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.productapp.entities.Product;
import com.productapp.entities.UserEntity;
import com.productapp.service.ProductService;
import com.productapp.service.UserService;

@SpringBootApplication
public class ProductappApplication implements CommandLineRunner{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productService.addProduct(new Product("laptop coolpad", 2000));
		productService.addProduct(new Product("dell laptop", 780000));
		
		userService.addUserEntity(new UserEntity("raj",passwordEncoder.encode("raj123"), List.of("ROLE_MGR","ROLE_ADMIN")));
		userService.addUserEntity(new UserEntity("ekta",passwordEncoder.encode("ekta123"), List.of("ROLE_MGR")));
		
		UserEntity user1=userService.findByUsername("raj");
		System.out.println(user1);
	}

}
