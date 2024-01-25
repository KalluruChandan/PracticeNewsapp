package com.newsapp.auth.service;

import com.newsapp.auth.model.User;
import com.newsapp.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepository authRepository;

    @Override
    public User registerUserToAuth(User user) {
        return authRepository.save(user);
    }
}
