package com.productapp.service;
import java.util.*;

import com.productapp.entities.Product;
public interface ProductService {
	public List<Product>  getAll();
	public Product getById(int id);
	public Product addProduct(Product product);
	public Product updateProduct(int id, Product product);
	public Product deleteProduct(int id);
	
	public List<Product>  getAllByName(String name);
	
}
