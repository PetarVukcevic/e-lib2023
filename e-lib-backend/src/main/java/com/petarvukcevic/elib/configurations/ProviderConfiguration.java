package com.petarvukcevic.elib.configurations;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "email")
public class ProviderConfiguration {

    private String host;
    private Integer port;
    private String username;
    private String password;
}
