package com.egar.client;

import com.egar.pojo.FXRateCorrectionCoefPojo;
import com.egar.pojo.JsonPojo;
import com.egar.pojo.Response;
import com.egar.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class JsonClient {

    private static final String PATH = "http://RestURL/api/v1/data/testdata";

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(JsonClient.class);

    public ResponseEntity<Response> putJson(FXRateCorrectionCoefPojo json) {
        HttpEntity<FXRateCorrectionCoefPojo> request = new HttpEntity<>(json);
        try {
            return restTemplate.exchange(PATH, HttpMethod.PUT, request, Response.class);
        } catch (ResourceAccessException e) {
            logger.warn("I/O error on PUT request",  e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
