package com.newsapp.auth.controller;

import com.newsapp.auth.custom.JwtAuthenticationResponse;
import com.newsapp.auth.model.User;
import com.newsapp.auth.repository.AuthRepository;
import com.newsapp.auth.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        path = "${auth.endpoint.common}"
)
@Slf4j
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(
            path = "${auth.endpoint.login}"
    )
    @Operation(
            summary = "Login here",
            description = "This endpoint will enable a registered user to login."
    )

    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
