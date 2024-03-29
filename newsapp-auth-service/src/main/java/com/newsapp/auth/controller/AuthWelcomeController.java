package com.newsapp.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "authApp")
public class AuthWelcomeController {

    @Value("${auth.welcome.message}")
    private String message;

    @GetMapping(
            path = "${auth.endpoint.welcome}"
    )
    @Operation(
            summary = "Get welcome message",
            description = "This endpoint is for testing purpose. It will return a welcome message"
    )
    public ResponseEntity<String> getWelcomeMessage(){
        return new ResponseEntity<>(
                message,
                HttpStatus.OK
        );
    }
}
