package com.newsapp.auth.model;

import com.newsapp.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = authRepository.findById(username);
        if(userFound.isPresent()){
            return UserDetailsImpl.builder()
                    .username(userFound.get().getUsername())
                    .password(userFound.get().getPassword())
                    .build();
        }
        else{
            throw new UsernameNotFoundException("User with given details not found");
        }
    }
}
