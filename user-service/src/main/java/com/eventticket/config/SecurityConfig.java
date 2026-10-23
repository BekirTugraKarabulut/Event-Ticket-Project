package com.eventticket.config;

import com.eventticket.security.AuthEntryPoint;
import com.eventticket.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(
                                "/users/register",
                                "/users/auth",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-ui/**"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .exceptionHandling((csrf) -> csrf.authenticationEntryPoint(authEntryPoint))
                .authenticationProvider(authenticationProvider)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
