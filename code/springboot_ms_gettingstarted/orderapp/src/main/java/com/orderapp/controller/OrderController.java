package com.orderapp.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.orderapp.config.MessageConfig;
import com.orderapp.dto.Coupon;
import com.orderapp.dto.Customer;
import com.orderapp.dto.OrderRequest;
import com.orderapp.dto.OrderResponse;
import com.orderapp.dto.Product;
import com.orderapp.service.CouponClientService;
import com.orderapp.service.CustomerClientService;
import com.orderapp.service.ProductClientService;

@RestController
public class OrderController {
	
	
	@Autowired
	private CouponClientService couponClientService;
	
	@Autowired
	private CustomerClientService customerClientService;
	
	@Autowired
	private ProductClientService productClientService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	//i want to create a post method ....
	@PostMapping(path = "orders")
	public ResponseEntity<OrderResponse> bookOrder(@RequestBody  OrderRequest orderRequest) {
		
		//call coupon service 
		Coupon coupon=couponClientService.getCoupon(orderRequest);
		
		//call product
		Product product=productClientService.getProduct(orderRequest);
		
		//call customer
		Customer customer=customerClientService.getCustomer(orderRequest);
		
		//we need to create the order and asyn rabbitMQ ...
		
		double discountedPrice= product.getPrice()-product.getPrice()*coupon.getDiscountPercentage()/100;
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setId(UUID.randomUUID().toString());
		orderResponse.setCustomer(customer);
		orderResponse.setProduct(product);
		orderResponse.setOrderDate(new Date());
		
		
		orderResponse.setTotalPrice(discountedPrice*orderRequest.getQty());
		
		rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, orderResponse);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}

}









