package com.demo.userservice.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.userservice.dto.RegisterBody;
import com.demo.userservice.dto.UserInfo;
import com.demo.userservice.exception.UserNotFoundException;
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

    public UserInfo verifyUser(String jwtToken) {
        var claims = tokenService.getClaimsFromToken(jwtToken).orElseThrow(() -> {
            throw new UserNotFoundException("User doesn't exist.", HttpStatus.UNAUTHORIZED);
        });

        UUID userId = UUID.fromString(claims.getSubject());

        var user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException("User doesn't exist.", HttpStatus.UNAUTHORIZED);
        });

        return new UserInfo("SUCESS", user.getUserId().toString(), user.getEmail());
    }

}
