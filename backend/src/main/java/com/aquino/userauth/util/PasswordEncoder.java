package com.aquino.userauth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordEncoder() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Hashes a raw password using BCrypt
     * @param rawPassword The plain text password
     * @return The hashed password
     */
    public String hashRawPassword(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    /**
     * Matches a raw password against a hashed password
     * @param rawPassword The plain text password
     * @param hashedPassword The hashed password from database
     * @return true if passwords match, false otherwise
     */
    public boolean matchesRawPassword(String rawPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, hashedPassword);
    }
}
