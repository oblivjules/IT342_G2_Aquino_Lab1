package com.aquino.userauth.security;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private final Map<String, Instant> blacklist = new ConcurrentHashMap<>();

    public void blacklist(String token, Instant expiresAt) {
        if (token == null || token.isBlank()) {
            return;
        }
        if (expiresAt != null && expiresAt.isBefore(Instant.now())) {
            return;
        }
        blacklist.put(token, expiresAt);
    }

    public boolean isBlacklisted(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        Instant expiresAt = blacklist.get(token);
        if (expiresAt == null) {
            return false;
        }
        if (expiresAt.isBefore(Instant.now())) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}
