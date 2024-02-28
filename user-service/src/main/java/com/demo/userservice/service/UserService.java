package com.demo.userservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.userservice.dto.RegisterBody;
import com.demo.userservice.model.User;
import com.demo.userservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterBody userInfo) {
        String encodedPassword = passwordEncoder.encode(userInfo.password());
        User user = User.builder()
                .email(userInfo.email())
                .password(encodedPassword)
                .build();
        userRepository.save(user);
        return tokenService.generateJwtToken(user.getUserId());
    }

}
