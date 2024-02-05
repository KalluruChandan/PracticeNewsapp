package com.newsapp.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidOrEmptyHeaderException.class)
        public ResponseEntity<String> inCaseOfInvalidOrEmptyHeaderException(Exception e){
        return new ResponseEntity<>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
