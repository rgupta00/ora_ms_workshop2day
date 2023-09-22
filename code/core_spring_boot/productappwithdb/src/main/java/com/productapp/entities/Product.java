package com.productapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product_table")
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "name can not be left blank")
	private String name;
	
	
	private double price;
	
	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
}
