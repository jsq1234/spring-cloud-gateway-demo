package com.demo.userservice.exception;

import java.lang.RuntimeException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmptyPasswordException extends RuntimeException {
    String message;

    public EmptyPasswordException(String message) {
        super(message);
        this.message = message;
    }
}
