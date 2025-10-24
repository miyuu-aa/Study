package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;

@Configuration
public class StudyConfig {

//	@Bean
//	UserService userService(UserRepository repository) {
//		return new UserService(repository);
//	}

	@Bean
	ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {

		return jackson2ObjectMapperBuilder.build() // 既存のObjectMapperを拡張

				.registerModule(
						new Hibernate6Module().configure(Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION, false)); // Hibernate6を追加
	}
}
