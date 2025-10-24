package com.example.demo.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
//(basePackages = "com.example.demo")
public class ResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ResponseBodyAdvice.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
		System.out.println("*****Rstart*****");
		
		Object body = bodyContainer.getValue();

		try {
			String json = objectMapper.writeValueAsString(body);
			logger.info("ResponseBody:{}", json);
		} catch (Exception e) {
			logger.warn("Failed to log response body", e);
		}
		
		System.out.println("*****Rend*****");
	}

	//		Map<String, Object> map = new HashMap<>();
	//		map.put("body", body);
	//		map.put("timestamp", System.currentTimeMillis());
	//		bodyContainer.setValue(map);

	//		try {
	//			if (body instanceof User user) {
	//				user.setFullName(user.getFirstName() + " " + user.getLastName());
	//				user.setGender(switch (user.getGenderCode()) {
	//				case 1 -> "男";
	//				case 2 -> "女";
	//				default -> "不明";
	//				});
	//				user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
	//				bodyContainer.setValue(user);
	//				String json = objectMapper.writeValueAsString(user);
	//				logger.info("ResponseBody: {}", json);
	//			} else {
	//				String json = objectMapper.writeValueAsString(body);
	//				logger.info("ResponseBody:　{}", json);
	//			}
	//		} catch (Exception e) {
	//			logger.warn("Failed to log response body", e);
	//		}

}
