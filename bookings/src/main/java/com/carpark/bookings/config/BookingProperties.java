package com.carpark.bookings.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bookings")
@Data
@ToString
public class BookingProperties {
    private String mgs;
}
