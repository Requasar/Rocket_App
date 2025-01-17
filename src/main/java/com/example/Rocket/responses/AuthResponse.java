package com.example.Rocket.responses;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private Long userId;
    private String token;

    // Full constructor
    public AuthResponse(String message, Long userId, String token) {
        this.message = message;
        this.userId = userId;
        this.token = token;
    }

    // Constructor for token only
    public AuthResponse(String token) {
        this.token = token;
    }
}

