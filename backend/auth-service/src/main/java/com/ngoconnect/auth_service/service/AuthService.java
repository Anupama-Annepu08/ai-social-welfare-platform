package com.ngoconnect.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ngoconnect.auth_service.dto.LoginRequest;
import com.ngoconnect.auth_service.dto.RegisterRequest;
import com.ngoconnect.auth_service.entity.User;
import com.ngoconnect.auth_service.repository.UserRepository;
import com.ngoconnect.auth_service.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String hashedPassword =
                passwordEncoder.encode(request.getPassword());

        user.setPasswordHash(hashedPassword);
        user.setRole(request.getRole());
        user.setEnabled(true);

        return userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!user.isEnabled()) {
            throw new RuntimeException("User account is disabled");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new RuntimeException("Invalid email or password");
        }

        return jwtService.generateToken(user);
    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}