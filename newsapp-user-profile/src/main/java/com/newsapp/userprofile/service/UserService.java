package com.newsapp.userprofile.service;

import com.newsapp.userprofile.custom.response.AppResponse;
import com.newsapp.userprofile.exception.UsernameTakenException;
import com.newsapp.userprofile.model.Action;
import com.newsapp.userprofile.model.Result;
import com.newsapp.userprofile.model.User;
import com.newsapp.userprofile.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String,User> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AppResponse register(User user) throws UsernameTakenException {
        Optional<User> userFound = userRepository.findById(user.getUsername());
        if(userFound.isPresent()){
            log.error("error occurred");
            throw new UsernameTakenException("Username provided is already registered. Try new Username......:)");
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userCreated = userRepository.save(user);
            kafkaTemplate.send("user-registration-topic",userCreated);

            return AppResponse.builder()
                    .actionPerformed(Action.REGISTER_A_USER)
                    .result(Result.SUCCESS)
                    .actionResponse(userCreated)
                    .build();
        }
    }
}
