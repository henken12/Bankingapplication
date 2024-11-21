//package com.bank.bankingapplication.client;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.util.Collections;
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class CustomRestService {
//
//    private final RestTemplate restTemplate;
//
//    @Value("${business-central-hex-encryption-url}")
//    private String bzHexEncryptionDtoUrl;
//
//    public CustomRestService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public <Req, Res> Optional<Res> executeRequest(String url, Req requestBody, HttpMethod method, HttpHeaders headers, Class<Res> resClazz) {
//        try {
//            log.info("Making a {} request to: {} with body: {}", method, url, maskSensitiveData(requestBody));
//
//            RequestEntity<Req> requestEntity = new RequestEntity<>(requestBody, headers, method, new URI(url));
//            ResponseEntity<Res> responseEntity = restTemplate.exchange(requestEntity, resClazz);
//
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                log.info("Request to {} succeeded with response: {}", url, maskSensitiveData(responseEntity.getBody()));
//                return Optional.ofNullable(responseEntity.getBody());
//            } else {
//                log.warn("Request to {} returned non-success status: {}", url, responseEntity.getStatusCode());
//            }
//        } catch (Exception ex) {
//            log.error("Error processing request to {}: {}", url, ex.getMessage(), ex);
//        }
//        return Optional.empty();
//    }
//
//    public <Res> Optional<Res> getRequest(String url, HttpHeaders headers, Class<Res> resClazz) {
//        return executeRequest(url, null, HttpMethod.GET, headers, resClazz);
//    }
//
//
//    public <Req, Res> Optional<Res> postRequest(String url, Req requestBody, HttpHeaders headers, Class<Res> resClazz) {
//        return executeRequest(url, requestBody, HttpMethod.POST, headers, resClazz);
//    }
//
//
//    public HttpHeaders createDefaultHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return headers;
//    }
//
//
//    private String maskSensitiveData(Object data) {
//        if (data == null) return "null";
//        try {
//            String jsonData = data.toString();
//            return jsonData.replaceAll("(\"password\"\\s*:\\s*\")[^\"]+\"", "$1****\"");
//        } catch (Exception e) {
//            return data.toString(); // Fallback to toString if masking fails
//        }
//    }
//}
