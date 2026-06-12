package com.possr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] endpointsToPermit = {
        "/continents/v1/getAll",
        "/continents/v1/create",
        "/continents/v1/update",
        "/continents/v1/delete/**",
        "/continents/v1/getById/**",
        "/continents/v1/getByPartialName/**",
        "/companies/v1/create",
        "/employees/v1/create",
        "/role-companies/v1/create",
        "/genres/v1/create",
        "/work-relations/v1/create",
        "/role-companies/v1/get-all-by-company-id/**",
        "/auth/v1/login"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(endpointsToPermit).permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
