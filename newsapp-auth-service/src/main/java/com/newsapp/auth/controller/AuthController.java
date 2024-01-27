package com.newsapp.auth.controller;


import com.newsapp.auth.payload.request.AuthRequestDTO;
import com.newsapp.auth.payload.response.JwtResponseDTO;
import com.newsapp.auth.repository.AuthRepository;
import com.newsapp.auth.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(
            path = "${auth.endpoint.login}"
    )
    @Operation(
            summary = "Login here",
            description = "This endpoint will enable a registered user to login."
    )
    public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponseDTO.builder()
                        .accessToken(jwtUtil.GenerateToken(authRequestDTO.getUsername()))
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }
}
