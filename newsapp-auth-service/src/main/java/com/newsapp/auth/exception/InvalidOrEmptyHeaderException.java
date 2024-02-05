package com.newsapp.auth.exception;

public class InvalidOrEmptyHeaderException extends RuntimeException{
    public InvalidOrEmptyHeaderException(String msg){
        super(msg);
    }
}
