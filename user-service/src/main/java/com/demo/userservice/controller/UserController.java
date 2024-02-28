package com.demo.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.userservice.dto.AuthResponse;
import com.demo.userservice.dto.RegisterBody;
import com.demo.userservice.dto.UserInfo;
import com.demo.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterBody userInfo) {
        String jwtToken = userService.registerUser(userInfo);
        AuthResponse response = new AuthResponse(HttpStatus.CREATED, jwtToken);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<UserInfo> validateUser(@RequestParam(name = "token", required = true) String jwtToken) {
        UserInfo userInfo = userService.verifyUser(jwtToken);
        return ResponseEntity.ok().body(userInfo);
    }
}
