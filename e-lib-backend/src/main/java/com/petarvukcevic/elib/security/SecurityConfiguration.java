package com.petarvukcevic.elib.security;

import com.petarvukcevic.elib.security.component.UnautorizedHttpEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity//(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private UnautorizedHttpEntryPoint unautorizedHttpEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // CSRF token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unautorizedHttpEntryPoint)
                .and()
                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .requestMatchers( "/categories/**").authenticated()
                .anyRequest().permitAll();

        return httpSecurity.build();
    }
}
