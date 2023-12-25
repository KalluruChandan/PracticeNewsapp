package com.newsapp.userprofile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileWelcomeController {

    @Value("${user-profile.welcome.message}")
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
