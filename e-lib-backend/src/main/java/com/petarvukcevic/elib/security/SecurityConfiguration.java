package com.petarvukcevic.elib.security;

import com.petarvukcevic.elib.security.component.UnauthorizedHttpEntryPoint;
import com.petarvukcevic.elib.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity//(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private UnauthorizedHttpEntryPoint unauthorizedHttpEntryPoint;

    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable() // CSRF token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHttpEntryPoint)
                .and()
                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
//                .requestMatchers( "/categories/**").authenticated()
                .requestMatchers(HttpMethod.POST,"/authenticate/**").permitAll() // login, register
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService) throws Exception
    {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // MD5
    }
}
