package com.example.demo.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	//	@ExceptionHandler(UserNotFoundException.class)
	//	public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException e) {
	//		System.out.println("userNotFound**************:");
	//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(), LocalDate.now()));
	//	}

	//	@ExceptionHandler(BusinessException.class)
	//  @ResponseStatus(HttpStatus.BAD_REQUEST) 
	//  public ErrorResponse handleUserNotFound(BusinessException e) {
	//        return new ErrorResponse(e.getMessage(), LocalDate.now());
	//    }

	//	@ExceptionHandler(BusinessException.class)
	//	//  @ResponseStatus(HttpStatus.BAD_REQUEST) 
	//	public ResponseEntity<ErrorResponse> handleUserNotFoundException(BusinessException e) {
	//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + LocalDate.now());
	//	}

	//	@ExceptionHandler(BusinessException.class)
	//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	//	public  ResponseEntity<ErrorResponse> handleUserNotFoundException(BusinessException e) {
	//	    return new ErrorResponse(e.getMessage(), LocalDate.now());
	//	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleBusinessException(BusinessException e) {
		return e.getResponse();
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		if (statusCode == HttpStatus.BAD_REQUEST)
			log.warn("BAD_REQUEST", ex);
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		// TODO 自動生成されたメソッド・スタブ
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
}
