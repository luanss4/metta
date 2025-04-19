package com.mega.erp.application.service;

import com.mega.erp.application.port.UserRepository;
import com.mega.erp.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAllActive();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Update basic info
                    existingUser.setName(updatedUser.getName());
                    existingUser.setEmail(updatedUser.getEmail());
                    
                    // Only update username if provided and different
                    if (updatedUser.getUsername() != null && !updatedUser.getUsername().equals(existingUser.getUsername())) {
                        // Check if new username is already taken
                        if (userRepository.existsByUsername(updatedUser.getUsername())) {
                            throw new IllegalArgumentException("Username already exists");
                        }
                        existingUser.setUsername(updatedUser.getUsername());
                    }
                    
                    // Only update password if provided
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    }
                    
                    // Only update roles if provided and user has permission (handled at controller level)
                    if (updatedUser.getRoles() != null && !updatedUser.getRoles().isEmpty()) {
                        existingUser.setRoles(updatedUser.getRoles());
                    }
                    
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresent(user -> {
                    user.setActive(false);
                    user.setDeletedAt(LocalDateTime.now());
                    userRepository.save(user);
                });
    }
    
    @Transactional
    public void resetPassword(String email, String newPassword) {
        userRepository.findByUsername(email)
                .ifPresent(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                });
    }
}