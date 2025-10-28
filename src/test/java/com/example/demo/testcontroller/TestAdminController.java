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
import com.example.demo.entity.Admin;

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestAdminController {

    @LocalServerPort
    int port;

    @Autowired
    RestClient restClient;

    @Test
    void createAdmin_Success() {
        Admin admin = new Admin();
        admin.setFirstName("Alice");
        admin.setLastName("Johnson");
        admin.setGenderCode(1);
        admin.setBirthday(LocalDate.now());
        admin.setRole("Manager");

        ResponseEntity<Admin> response = restClient.post()
                .uri("http://localhost:{port}/admin", port)
                .contentType(MediaType.APPLICATION_JSON)
                .body(admin)
                .retrieve()
                .toEntity(Admin.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
