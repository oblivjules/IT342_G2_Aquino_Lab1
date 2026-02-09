package com.aquino.userauth.dto;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    public UserResponse(Long id, String username, String email, String firstName, String lastName, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
