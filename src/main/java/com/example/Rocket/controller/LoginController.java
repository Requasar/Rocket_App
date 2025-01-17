package com.example.Rocket.controller;


import com.example.Rocket.dto.UserDto;
import com.example.Rocket.requests.UserRequest;
import com.example.Rocket.responses.AuthResponse;
import com.example.Rocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final UserService userService;

    private UserRequest userRequest;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        try {
            System.out.println("Authenticate method triggered");

            String token = userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());

            return ResponseEntity.ok("Login successful");

        } catch (Exception e) {
            // Hata durumunda ilgili mesaj döndürülür
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}