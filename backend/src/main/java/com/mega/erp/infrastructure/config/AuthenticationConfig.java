package com.mega.erp.infrastructure.config;

import com.mega.erp.application.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final AuthenticationService authenticationService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @PostConstruct
    public void init() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        authenticationService.setAuthenticationManager(authenticationManager);
    }
}