package com.brunofr.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component("restClient")
public class RestClient {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public RestClient(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public <T> T getForEntity(Class<T> clazz, String url, Object... uriVariables) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
            JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
            return readValue(response, javaType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("No data found {}"+url);
            } else {
                System.out.println("rest client exception" + exception.getMessage());
            }
        }
        return null;
    }

    public <T> List<T> getForList(Class<T> clazz, String url, Object... uriVariables) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, uriVariables);
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return readValue(response, collectionType);
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("No data found {}"+ url);
            } else {
                System.out.println("rest client exception"+exception.getMessage());
            }
        }
        return null;
    }

    public <T, R> T postForEntity(Class<T> clazz, String url, R body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("shop-codes", "428322");

        HttpEntity<R> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }

    public <T, R> T putForEntity(Class<T> clazz, String url, R body, Object... uriVariables) {
        HttpEntity<R> request = new HttpEntity<>(body);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class, uriVariables);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }

    public void delete(String url, Object... uriVariables) {
        try {
            restTemplate.delete(url, uriVariables);
        } catch (RestClientException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK ||
                response.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(response.getBody(), javaType);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("No data found {}"+ response.getStatusCode());
        }
        return result;
    }

}
