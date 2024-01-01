package com.newsapp.wishlist.controller;

import com.newsapp.wishlist.custom.response.ActionResponse;
import com.newsapp.wishlist.model.Action;
import com.newsapp.wishlist.model.Result;
import io.swagger.v3.oas.annotations.Operation;
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
            path = "${wishlist.endpoint.welcome}"
    )
    @Operation(
            summary = "Get welcome message",
            description = "This endpoint is for testing purpose. It will return a welcome message"
    )
    public ResponseEntity<ActionResponse> getWelcomeMessage(){

        //Build ActionResponse
        ActionResponse actionResponse = new ActionResponse(
                Action.GET_WELCOME_MESSAGE,
                Result.SUCCESS,
                message,
                HttpStatus.OK
        );
        return new ResponseEntity<>(
                actionResponse,
                HttpStatus.OK
        );
    }
}
