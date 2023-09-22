package com.productapp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorInfo {
	private String message;
	private int statusCode;
	private LocalDateTime timestamp;
	private String toContact;
}
