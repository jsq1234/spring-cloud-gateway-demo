package com.demo.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.demo.gateway.config.JwtUtils.UserInfo;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

    private final JwtUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // for /auth/register, don't do any authentication
        if (request.getURI().getPath().equals("/register")) {
            return chain.filter(exchange);
        }

        if (authMissing(request)) {
            return sendError(response, HttpStatus.UNAUTHORIZED);
        }

        String jwtToken = getAuthHeader(request);

        // // Get claims from jwt token
        // Claims claims = jwtUtils.getClaimsFromToken(jwtToken).orElse(null);

        // // claims == null implies parsing of jwt valid, thus an invalid jwt token
        // if (claims == null) {
        // return sendError(response, HttpStatus.UNAUTHORIZED);
        // }

        // // add userId to the request so that the proxied service can use it if need
        // // arises
        // attachUserIdToResponse(request, claims);

        var userInfo = jwtUtils.validateJwt(jwtToken);

        if (userInfo == null) {
            return sendError(response, HttpStatus.UNAUTHORIZED);
        }

        attachUserIdToResponse(request, userInfo);

        return chain.filter(exchange);
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private Mono<Void> sendError(ServerHttpResponse response, HttpStatus status) {
        response.setStatusCode(status);
        return response.setComplete();
    }

    private void attachUserIdToResponse(ServerHttpRequest request, UserInfo userInfo) {
        request.mutate().header("user_id", userInfo.email())
                .header("email", userInfo.email()).build();

    }

}
