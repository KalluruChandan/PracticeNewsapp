package com.newsapp.auth.service;

import com.newsapp.auth.model.User;
import com.newsapp.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthRepository authRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                Optional<User> userFound = authRepository.findById(username);
                if(userFound.isEmpty()){
                    throw new UsernameNotFoundException("User not found");
                }
                else{
                    return User.builder()
                            .username(userFound.get().getUsername())
                            .password(userFound.get().getPassword())
                            .build();
                }
            }
        };
    }
}
