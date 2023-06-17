package com.carpark.accounts.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "accounts")
@Data
@ToString
public class AccountProperties {
    private String mgs;
}
