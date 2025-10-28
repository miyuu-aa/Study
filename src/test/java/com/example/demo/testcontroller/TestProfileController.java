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
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest(classes = UserApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class TestProfileController {

	@LocalServerPort
    int port;

    @Autowired
    RestClient restClient;

    @Autowired
    UserRepository userRepository;
    
    @Test
    void createProfile_Success() {
    	User user = new Customer("Alice", "Smith", 2, LocalDate.of(2008,5,14), 10L);
    	user = userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setBiography("Hello,test.");

        ResponseEntity<Profile> response = restClient.post()
                .uri("http://localhost:{port}/profile", port)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profile)
                .retrieve()
                .toEntity(Profile.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
    @Test
    void getProfileById_Success() {
        ResponseEntity<Profile> response = restClient.get()
                .uri("http://localhost:{port}/profile/{id}", port, 1)
                .retrieve()
                .toEntity(Profile.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

}
