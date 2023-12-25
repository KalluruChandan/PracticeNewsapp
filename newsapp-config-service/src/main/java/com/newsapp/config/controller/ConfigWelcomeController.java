package com.newsapp.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigWelcomeController {

    @Value("${config.welcome.message}")
    private String message;

    @GetMapping(
            path = "/welcome"
    )
    public ResponseEntity<String> getWelcomeMessage(){
        return new ResponseEntity<>(
                message,
                HttpStatus.OK
        );
    }

}
