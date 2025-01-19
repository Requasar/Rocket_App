package com.example.Rocket.responses;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private Long userId;
    private String token;
    private String role;

    // Full constructor
    public AuthResponse() {
        this.message = message;
        this.userId = userId;
        this.token = token;
        this.role = role;
    }

    // Constructor for token only
    public AuthResponse(String token) {
        this.token = token;
    }
}
