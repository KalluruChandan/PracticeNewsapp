package com.newsapp.wishlist.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistWelcomeController {

    @Value("${wishlist.welcome.message}")
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
