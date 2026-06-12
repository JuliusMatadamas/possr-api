package com.possr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppProperties appProperties;

    public SecurityConfig(
            AppProperties appProperties
    ) {
        this.appProperties = appProperties;
    }

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
        "/genres/v1/getAll",
        "/work-relations/v1/create",
        "/role-companies/v1/get-all-by-company-id/**",
        "/auth/v1/login"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http
                .cors(cors -> cors.configurationSource(
                        corsConfigurationSource()
                ))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(endpointsToPermit).permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(
                appProperties.getAllowedOrigins()
        );
        configuration.setAllowedMethods(
                appProperties.getAllowedMethods()
        );
        configuration.setAllowedHeaders(
                appProperties.getAllowedHeaders()
        );
        configuration.setExposedHeaders(
                appProperties.getExposedHeaders()
        );
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}
