package com.productapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.productapp.entities.Product;
import com.productapp.exceptions.ProductNotFoundException;
import com.productapp.repo.ProductRepo;
@Service
@Transactional//ACID
public class ProductServiceImpl implements ProductService{

	private ProductRepo productRepo;
	
	@Autowired
	public ProductServiceImpl(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> getAll() {
		/*
		 * if(1==1) throw new RuntimeException();
		 */
		return productRepo.findAll();
	}

	@Override
	public Product getById(int id) {
		return productRepo.findById(id)
				.orElseThrow(()-> new ProductNotFoundException("product with id "+ id +" is not found"));
	}

	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product updateProduct(int id, Product product) {
		Product productToUpdate= getById(id);
		productToUpdate.setPrice(product.getPrice());
		
		productRepo.save(productToUpdate);
		
		
		return productToUpdate;
	}

	@Override
	public Product deleteProduct(int id) {
		Product productToDelete= getById(id);
		productRepo.delete(productToDelete);
		return productToDelete;
	}

	@Override
	public List<Product> getAllByName(String name) {
		return productRepo.findByName(name);
	}

}







