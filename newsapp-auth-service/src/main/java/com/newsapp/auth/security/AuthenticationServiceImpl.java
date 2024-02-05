package com.newsapp.auth.security;

import com.newsapp.auth.custom.JwtAuthenticationResponse;
import com.newsapp.auth.model.User;
import com.newsapp.auth.repository.AuthRepository;
import com.newsapp.auth.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(User request) {
        var user = User.builder().username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        authRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = authRepository.findById(request.getUsername());
        if(user.isEmpty()){
            throw new IllegalArgumentException("Invalid email or password");
        }
        var jwt = jwtService.generateToken(user.get());
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}