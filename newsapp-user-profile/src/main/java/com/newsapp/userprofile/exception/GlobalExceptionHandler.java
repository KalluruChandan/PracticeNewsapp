package com.newsapp.userprofile.exception;

import com.newsapp.userprofile.custom.response.AppResponse;
import com.newsapp.userprofile.model.Action;
import com.newsapp.userprofile.model.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<AppResponse> invokeWhenUserNameIsTaken(Exception e){
        AppResponse appResponse = AppResponse.builder()
                .actionPerformed(Action.REGISTER_A_USER)
                .actionResponse(e.getMessage())
                .result(Result.FAILED)
                .build();
        return new ResponseEntity<>(
                appResponse,
                HttpStatus.CONFLICT
        );
    }
}
