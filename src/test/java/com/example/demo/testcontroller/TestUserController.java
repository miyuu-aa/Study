package com.example.demo.testcontroller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.example.demo.TestConfig;
import com.example.demo.UserApplication;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestUserController {

	@LocalServerPort
	int port;

	@Autowired
	RestClient restClient;

	@Test
	void getUsers_ReturnsList() {
		List<User> users = restClient.get()
				.uri("http://localhost:{port}/user", port)
				.retrieve()
				.body(new ParameterizedTypeReference<List<User>>() {
				});
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	void getUserById_ReturnsUser() {
		ResponseEntity<User> result = restClient.get()
				.uri("http://localhost:{port}/user/{id}", port, 1)
				.retrieve()
				.toEntity(User.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).isNotNull();
		assertThat(result.getBody().getId()).isEqualTo(1);
	}

	@Test
	void createUser_ReturnsCreatedUser() {
		User user = new Customer("Alice", "Smith", 2, LocalDate.of(2008, 5, 14), 10L);
		ResponseEntity<Customer> result = restClient.post()
				.uri("http://localhost:{port}/user", port)
				.contentType(MediaType.APPLICATION_JSON)
				.body(user)
				.retrieve()
				.toEntity(Customer.class);
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(result.getBody()).isNotNull();
	}

	@Test
	void deleteUser_Success() {
		ResponseEntity<Void> result = restClient.delete()
				.uri("http://localhost:{port}/user/{id}", port, 1)
				.retrieve()
				.toBodilessEntity();
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

	@Test
	void updateUser_Success() {
		User user = new Customer("Bob", "Johnson", 2, LocalDate.of(2010, 7, 21), 10L);
		ResponseEntity<Customer> result = restClient.put()
				.uri("http://localhost:{port}/user/{id}", port, 3)
				.contentType(MediaType.APPLICATION_JSON)
				.body(user)
				.retrieve()
				.toEntity(Customer.class);
		assertAll(
				() -> assertEquals(HttpStatus.OK, result.getStatusCode()),
				() -> assertNotNull(result.getBody()));
		assertThat(result.getBody().getFirstName()).isEqualTo("Bob");
		assertThat(result.getBody().getLastName()).isEqualTo("Johnson");
	}

	@Test
	void getUserById_NotFound() {
		RestClientResponseException e = assertThrows(RestClientResponseException.class,
				() -> restClient.get().uri("http://localhost:{port}/user/{id}", port, 10000)
						.retrieve().toEntity(String.class));
		assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		assertTrue(e.getResponseBodyAsString().contains("Data Not Found"));
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
