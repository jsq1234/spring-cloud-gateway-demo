package com.demo.userservice.dto;

import org.springframework.http.HttpStatus;

public record AuthResponse(HttpStatus status, String token) {

}
