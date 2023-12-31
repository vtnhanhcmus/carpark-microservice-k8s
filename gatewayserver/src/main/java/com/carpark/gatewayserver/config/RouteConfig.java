package com.carpark.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gateWayRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("accounts-route", r -> r.path("/carparks/accounts/**").filters(f -> f.rewritePath("/carparks/accounts/(?<segment>.*)","/accounts/${segment}")).uri("lb://accounts"))
                .route("bookings-route", r -> r.path("/carparks/bookings/**").filters(f -> f.rewritePath("/carparks/bookings/(?<segment>.*)","/bookings/${segment}")).uri("lb://bookings"))
                .route("carparks-info-route", r -> r.path("/carparks/main/**").filters(f -> f.rewritePath("/carparks/main/(?<segment>.*)","/carpark/${segment}")).uri("lb://carparks"))
                .route("carparks-availability-route", r -> r.path("/carparks/availability/**").filters(f -> f.rewritePath("/carparks/availability/(?<segment>.*)","/availability/${segment}")).uri("lb://carparks"))
                .build();
    }

}
