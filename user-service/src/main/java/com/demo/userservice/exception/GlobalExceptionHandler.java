package com.demo.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex) {
        log.info("Unauthorized Acess : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        log.info("Unauthorized Access : {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
