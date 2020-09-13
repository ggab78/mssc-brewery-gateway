package com.gabriel.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("google")
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator ngoogleConfig(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/wp").uri("http://wp.pl").id("wp"))
                .route(r->r.path("/gg").filters(f->f.rewritePath("/gg(?<segment>/?.*)","/${segment}")).uri("https://google.com").id("google"))
                .build();
    }

}
