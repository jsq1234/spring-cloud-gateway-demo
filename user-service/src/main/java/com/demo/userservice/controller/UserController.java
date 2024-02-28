package com.demo.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.userservice.dto.AuthResponse;
import com.demo.userservice.model.User;
import com.demo.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) {
        String jwtToken = userService.registerUser(user);
        AuthResponse response = new AuthResponse(HttpStatus.CREATED, jwtToken);
        return ResponseEntity.status(201).body(response);
    }
}
