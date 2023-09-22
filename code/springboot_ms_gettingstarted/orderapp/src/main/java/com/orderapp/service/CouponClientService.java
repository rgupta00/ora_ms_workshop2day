package com.orderapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderapp.dto.Coupon;
import com.orderapp.dto.OrderRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CouponClientService {

	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(fallbackMethod = "getCouponFallback", name = "couponservice")
	public Coupon getCoupon(OrderRequest orderRequest) {
		Coupon coupon=restTemplate
				.getForObject("http://COUPON-SERVICE/couponapp/coupons/"+orderRequest.getCouponCode(),
						Coupon.class);
		
		return coupon;
	}
	
	public Coupon getCouponFallback(OrderRequest orderRequest) {
		Coupon coupon=new Coupon();
		coupon.setCouponCode("SUP02");
		coupon.setDiscountPercentage(2);
		coupon.setExpiredOn(new Date());
		coupon.setId(111);
		return coupon;
	}
}
