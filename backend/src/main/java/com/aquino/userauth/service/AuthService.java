package com.aquino.userauth.service;

import com.aquino.userauth.dto.AuthResponse;
import com.aquino.userauth.dto.LoginRequest;
import com.aquino.userauth.dto.RegisterRequest;
import com.aquino.userauth.model.User;
import com.aquino.userauth.repository.UserRepository;
import com.aquino.userauth.security.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getIdentifier());
        if (userOptional.isEmpty()) {
            userOptional = userRepository.findByEmail(request.getIdentifier());
        }

        User user = userOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = tokenProvider.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
