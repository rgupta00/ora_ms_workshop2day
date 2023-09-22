package com.productapp.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.productapp.dto.ErrorInfo;
import com.productapp.exceptions.ProductNotFoundException;

@RestControllerAdvice //AOP
public class ProductExHandler {
	
	//productNotFoundEx
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorInfo> handle404(ProductNotFoundException ex){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setMessage(ex.getMessage());
		errorInfo.setStatusCode(404);
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setToContact("raj@oracle.com");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
	}
	
	//@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handle500(Exception ex){
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setMessage("pls try after some time");
		errorInfo.setStatusCode(500);
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setToContact("raj@oracle.com");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
	}
	//MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> handle400(MethodArgumentNotValidException ex){
		ErrorInfo errorInfo=new ErrorInfo();
		
		//We have to find out why validation failed and give that info to the app user
		String message= ex.getBindingResult().getAllErrors()
				.stream().map(x->x.getDefaultMessage())
				.collect(Collectors.joining(" ,"));
		
		errorInfo.setMessage(message);
		
		
		errorInfo.setStatusCode(400);
		
		errorInfo.setTimestamp(LocalDateTime.now());
		errorInfo.setToContact("raj@oracle.com");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
	}
	
	
}
















