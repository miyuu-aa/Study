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

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestCustomerController {

    @LocalServerPort
    int port;

    @Autowired
    RestClient restClient;

    @Test
    void createCustomer_Success() {
        Customer customer = new Customer("Alice","Smith",2,LocalDate.of(2008,5,14),10L);

        ResponseEntity<Customer> response = restClient.post()
                .uri("http://localhost:{port}/customer", port)
                .contentType(MediaType.APPLICATION_JSON)
                .body(customer)
                .retrieve()
                .toEntity(Customer.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
