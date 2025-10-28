package com.example.demo.testcontroller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
import com.example.demo.entity.Customer;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestPostController {

	@LocalServerPort
	int port;

	@Autowired
	RestClient restClient;

	@Autowired
	UserRepository userRepository;

	@Test
	void createPost_Success() {
		User user = new Customer("Alice", "Smith", 2, LocalDate.of(2008, 5, 14), 10L);
		user = userRepository.save(user);
		Post post = new Post();
		post.setUser(user);
		post.setContent("This is a test post.");

		ResponseEntity<Post> response = restClient.post()
				.uri("http://localhost:{port}/post", port)
				.contentType(MediaType.APPLICATION_JSON)
				.body(post)
				.retrieve()
				.toEntity(Post.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}
