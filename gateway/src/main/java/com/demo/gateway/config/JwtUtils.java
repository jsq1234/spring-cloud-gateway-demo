package com.demo.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    // @Value("${jwt.secret}")
    // private String secret;

    // private SecretKey key;

    // @PostConstruct
    // public void init() {
    // this.key = Keys.hmacShaKeyFor(secret.getBytes());
    // }

    // public Optional<Claims> getClaimsFromToken(String token) {

    // JwtParser jwtParser = Jwts.parser().verifyWith(key).build();
    // try {
    // return Optional.of(jwtParser.parseSignedClaims(token).getPayload());
    // } catch (JwtException | IllegalArgumentException e) {
    // }
    // return Optional.empty();
    // }

    private final RestTemplate restTemplate;

    public record UserInfo(String userId, String email) {
    }

    public UserInfo validateJwt(String jwtToken) {
        String userServiceUrl = "http://user-service:8081/validate?=" + jwtToken;
        ResponseEntity<UserInfo> responseEntity = restTemplate.getForEntity(userServiceUrl, UserInfo.class);

        if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return null;
        }
        return responseEntity.getBody();
    }
}
