package com.mega.erp.infrastructure.config;

import com.mega.erp.application.port.UserRepository;
import com.mega.erp.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user if it doesn't exist
        if (!userRepository.existsByUsername("luan")) {
            User adminUser = new User();
            adminUser.setUsername("luan");
            adminUser.setPassword(passwordEncoder.encode("030479"));
            adminUser.setName("Luan Administrator");
            adminUser.setEmail("admin@mega.com");
            
            Set<String> roles = new HashSet<>();
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
            adminUser.setRoles(roles);
            
            userRepository.save(adminUser);
            
            System.out.println("Admin user created successfully");
        }
    }
}