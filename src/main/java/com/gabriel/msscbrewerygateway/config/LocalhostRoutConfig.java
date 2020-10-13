package com.gabriel.msscbrewerygateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalhostRoutConfig {

    @Bean
    public RouteLocator localhostRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*","/api/v1/beer/upc/*")
                        .uri("http://localhost:8080")
                        .id("localhost"))
                .build();


    }


}
