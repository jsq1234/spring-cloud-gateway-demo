package com.demo.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.util.Optional;
import javax.crypto.SecretKey;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Optional<Claims> getClaimsFromToken(String token) {

        JwtParser jwtParser = Jwts.parser().verifyWith(key).build();
        try {
            return Optional.of(jwtParser.parseSignedClaims(token).getPayload());
        } catch (JwtException | IllegalArgumentException e) {
        }
        return Optional.empty();
    }
}
