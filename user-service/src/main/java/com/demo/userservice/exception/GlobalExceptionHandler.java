package com.demo.userservice.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
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

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(ConstraintViolationException ex) {
        log.info("User already exists: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<String> handleNullConstraintViolation(DataIntegrityViolationException ex) {
        String errMessage = ex.getMessage();
        if (errMessage.contains("email")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expected email in body.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = EmptyPasswordException.class)
    public ResponseEntity<String> handleNoPasswordException(EmptyPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expected password in body.");
    }

}
