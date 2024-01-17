package com.newsapp.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        path = "${auth.endpoint.common}"
)
public class AuthController {

    @PostMapping(
            path = "${auth.endpoint.login}"
    )
    @Operation(
            summary = "Login here",
            description = "This endpoint will enable a registered user to login."
    )
    public void login(){

    }
}
