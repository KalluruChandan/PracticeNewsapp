package com.newsapp.auth.controller;

import com.newsapp.auth.model.User;
import com.newsapp.auth.repository.AuthRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping(
            path = "${auth.endpoint.login}"
    )
    @Operation(
            summary = "Login here",
            description = "This endpoint will enable a registered user to login."
    )
    public List<User> login(){
        List<User> allUsers = authRepository.findAll();
        log.info("----------"+passwordEncoder.matches("chan123",allUsers.get(0).getPassword()));
        return allUsers;
    }
//    public void login(){}
}
