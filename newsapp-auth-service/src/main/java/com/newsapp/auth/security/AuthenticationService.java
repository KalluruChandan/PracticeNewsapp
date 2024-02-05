package com.newsapp.auth.security;

import com.newsapp.auth.custom.JwtAuthenticationResponse;
import com.newsapp.auth.model.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(User request);

    JwtAuthenticationResponse signin(User request);
}
