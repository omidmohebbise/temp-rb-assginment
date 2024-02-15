package com.rbank.bank.controller;


import com.rbank.bank.service.dto.CreateAccount;
import com.rbank.util.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountControllerIntegrationTest extends BaseIntegrationTest {


    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void testCreatAccount() {
        // Create the request body for the create account request
        CreateAccount createAccount = new CreateAccount( "John Doe", 1000.0, 1L);

        // Create a WebClient instance to send the request
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:" + port)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + generateToken())
                .build();

        // Send a POST request to the create account endpoint
        ResponseEntity<String> responseEntity = webClient.post()
                .uri("/api/accounts")
                .bodyValue(createAccount)
                .retrieve()
                .toEntity(String.class)
                .block();

        // Assert the response status code
        assertEquals(HttpStatus.CREATED, Objects.requireNonNull(responseEntity).getStatusCode());

        // Optionally, assert the response body or other properties
        assertNotNull(responseEntity.getBody());
    }




}