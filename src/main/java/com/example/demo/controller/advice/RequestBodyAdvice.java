package com.example.demo.controller.advice;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class RequestBodyAdvice extends RequestBodyAdviceAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RequestBodyAdvice.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		try {
			String json = objectMapper.writeValueAsString(body);
			logger.info("RequestBody:{}", json);
		} catch (Exception e) {
			logger.warn("Failed to log request body", e);
		}
		return body;
	}

}
