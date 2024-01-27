package com.newsapp.auth.controller;

import com.newsapp.auth.jwtUtil.JwtHelper;
import com.newsapp.auth.model.LoginRequest;
import com.newsapp.auth.model.LoginResponse;
import com.newsapp.auth.model.User;
import com.newsapp.auth.model.UserDetailsImpl;
import com.newsapp.auth.repository.AuthRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping(
            path = "${auth.endpoint.login}"
    )
    @Operation(
            summary = "Login here",
            description = "This endpoint will enable a registered user to login."
    )
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        log.debug("entered login()");

//        this.doAuthenticate(loginRequest.getUsername(), loginRequest.getPassword());

        UserDetails userDetails = userDetailsBuilder(loginRequest);
        String token = jwtHelper.generateToken(userDetails);

        LoginResponse response = LoginResponse.builder()
                .username(loginRequest.getUsername())
                .token(token)
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }


    private void doAuthenticate(String email, String password) {

        log.debug("Entered doAuthenticate()");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        }
        catch (BadCredentialsException e) {
            log.debug("BadCredentialsException "+e.toString());
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
        log.debug("left doAuthenticate()");
    }

    private UserDetails userDetailsBuilder(LoginRequest request){
        return UserDetailsImpl.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
