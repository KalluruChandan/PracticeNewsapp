package com.newsapp.userprofile.exception;

public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String s) {
        super(s);
    }
}
