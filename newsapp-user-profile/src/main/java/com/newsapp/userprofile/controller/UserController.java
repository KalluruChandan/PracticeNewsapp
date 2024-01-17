package com.newsapp.userprofile.controller;


import com.newsapp.userprofile.custom.response.AppResponse;
import com.newsapp.userprofile.model.Action;
import com.newsapp.userprofile.model.Result;
import com.newsapp.userprofile.model.User;
import com.newsapp.userprofile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(
        path = "/newsapp/v1/user-profile"
)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/register"
    ) //    localhost:8102/newsapp/v1/user-profile/register
    @Operation(
            summary = "register a user",
            description = "This is an endpoint to register an user to the Newsapp."
    )

    public ResponseEntity<AppResponse> register(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            return  new ResponseEntity<>(
                    AppResponse.builder()
                            .actionPerformed(Action.REGISTER_A_USER)
                            .result(Result.ERROR)
                            .actionResponse(errors)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                userService.register(user),
                HttpStatus.CREATED
        );
    }
}
