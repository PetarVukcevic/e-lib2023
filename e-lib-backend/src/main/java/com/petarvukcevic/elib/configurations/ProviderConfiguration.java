package com.petarvukcevic.elib.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class ProviderConfiguration {

    private String host;
    private String port;
    private String username;
    private String password;
}
