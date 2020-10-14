package com.gabriel.msscbrewerygateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.codec.ServerCodecConfigurer;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutConfig {

    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(r -> r.path("/api/v1/beer", "/api/v1/beer/*","/api/v1/beer/upc/*")
                        .uri("lb://beer-service")
                        .id("beer-service"))
                .route(r->r.path("/api/v1/customers/*/orders","/api/v1/customers/*/orders/*","/api/v1/customers/*/orders/*/pickup" )
                        .uri("lb://order-service")
                        .id("order-service"))
                .route(r->r.path("/api/v1/beer/*/inventory")
                        .uri("lb://inventory-service")
                        .id("inventory-service"))
                .build();

    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }

}
