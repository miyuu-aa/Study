package com.example.demo.exception;

import com.example.demo.dto.ErrorResponse;

import lombok.Getter;

public class BusinessException extends RuntimeException {

	@Getter
	public ErrorResponse response;

	public BusinessException(ErrorResponse response) {
		this.response = response;
	}
}
