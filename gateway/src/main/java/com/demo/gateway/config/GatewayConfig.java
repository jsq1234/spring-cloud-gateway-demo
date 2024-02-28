package com.demo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service",
                        r -> r.path("/auth/register")
                                .filters(f -> f.stripPrefix(1))
                                .uri("http://user-service:8081/"))
                .route("posts-service",
                        r -> r.path("/posts/**")
                                .filters(f -> f.stripPrefix(1))
                                .uri("http://posts-service:8082/"))
                .build();
    }
}
