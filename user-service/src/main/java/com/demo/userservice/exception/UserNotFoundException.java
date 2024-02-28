package com.demo.userservice.exception;

import java.lang.RuntimeException;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public UserNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
