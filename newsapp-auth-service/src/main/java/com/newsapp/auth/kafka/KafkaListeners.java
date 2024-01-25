package com.newsapp.auth.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsapp.auth.model.User;
import com.newsapp.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    @Autowired
    private AuthService authService;

    @KafkaListener(
            topics = "${auth.kafka.topic}",
            groupId = "${auth.kafka.group-id}"
    )
    public void consume(ConsumerRecord<String, User> record){
        try{
            String str = String.valueOf(record.value());
            ObjectMapper objectMapper = new ObjectMapper();
            User userConsumed = objectMapper.readValue(str, User.class);
            authService.registerUserToAuth(userConsumed);
            log.info("User added to the db successfully.  :)");
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
