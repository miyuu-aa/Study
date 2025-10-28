package com.example.demo.testcontroller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.example.demo.TestConfig;
import com.example.demo.UserApplication;
import com.example.demo.entity.Group;

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestGroupController {

	@LocalServerPort
	int port;

	@Autowired
	RestClient restClient;

	@Test
	void createGroup_Success() {
		Group group = new Group();
		group.setName("Test Group");

		ResponseEntity<Group> response = restClient.post()
				.uri("http://localhost:{port}/group", port)
				.contentType(MediaType.APPLICATION_JSON)
				.body(group)
				.retrieve()
				.toEntity(Group.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}
