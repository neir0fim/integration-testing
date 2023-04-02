package com.kuzin.integrationtesting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter()).and()
                .and().cors()
                .and().csrf().disable()
                .build();
    }
}
