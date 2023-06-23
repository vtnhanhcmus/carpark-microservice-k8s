package com.carpark.carparks.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "carparks")
@Data
@ToString
public class CarparkProperties {
    private String mgs;
}
