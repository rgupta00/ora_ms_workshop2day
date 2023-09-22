package com.productapp.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productapp.entities.Product;
import com.productapp.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//how to return correct status code to the client?
	//ResponseEntity<T> is a wrapper which hold 2 thing data + status code

	//=========get all products===========
	@GetMapping(path = "products")
	public List<Product> getAll(){
		
		return productService.getAll();
	}
	
	//=========get an product by id===========
	@GetMapping(path = "products/{id}")
	public Product getById( @PathVariable(name="id")  int id){
		return productService.getById(id);
	}
	
	//=========add new product===========
	@PostMapping(path = "products")
	public ResponseEntity<Product> addProduct( @RequestBody @Valid Product product){
		Product productAdded= productService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productAdded);
	}
	
	//=========update product===========
	@PutMapping(path = "products/{id}")
	public Product updateProduct( @PathVariable(name="id")  int id,@RequestBody Product product){
		return productService.updateProduct(id, product);
	}
	
	
	//=========delete product===========
	@DeleteMapping(path = "products/{id}")
	public ResponseEntity<Void> deleteById( @PathVariable(name="id")  int id){
		 productService.deleteProduct(id);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}







