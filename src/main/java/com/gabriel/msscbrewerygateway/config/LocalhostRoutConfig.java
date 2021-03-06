package com.gabriel.msscbrewerygateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalhostRoutConfig {

    @Bean
    public RouteLocator localhostRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*","/api/v1/beer/upc/*")
                        .uri("http://localhost:8080")
                        .id("beer-service"))
                        .route(r->r.path("/api/v1/customers/*/orders","/api/v1/customers/*/orders/*","/api/v1/customers/*/orders/*/pickup" )
                        .uri("http://localhost:8081")
                        .id("order-service"))
                        .route(r->r.path("/api/v1/beer/*/inventory")
                        .uri("http://localhost:8082")
                        .id("inventory-service"))
                .build();

    }


}
