package com.delivery.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponse {
	private String id;
	private double totalPrice;
	private Date orderDate;
	private Customer customer;
	private Product product;

}