package com.bank.bankingapplication.client;

import com.bank.bankingapplication.model.response.UserResponse;
import com.bank.bankingapplication.model.User;
import com.bank.bankingapplication.security.UserRequest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;


@Component
@Slf4j
public class HttpClient {

    @Autowired
    protected RestTemplate restTemplate;


    @Value("${user-details-url}")
    private String userSignupUrl;


    public User fetchUser(String email) throws URISyntaxException {
        log.info("Fetching user by user email {}, url: {}", email, userSignupUrl);

        // Build the URL with the query parameter
        URI uri = new URI(userSignupUrl + "?email=" + email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Make a GET request with the constructed URI
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                UserResponse.class
        );

        log.info("Response status: {}", response.getStatusCode());

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getUser();
        } else {
            throw new RuntimeException("Failed to fetch user: " + response.getStatusCode());
        }
    }


}
