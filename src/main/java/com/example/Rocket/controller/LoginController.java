package com.example.Rocket.controller;

import com.example.Rocket.requests.UserRequest;
import com.example.Rocket.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

//    private final UserService userService;
//
//    @Autowired
//    public LoginController(UserService userService) {
//        this.userService = userService;
//    }
//
////    @GetMapping("/csrf-token")
////    public CsrfToken getCsrfToken(HttpServletRequest request) {
////        return (CsrfToken) request.getAttribute("_csrf");
////    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
//        try {
//            System.out.println("Authenticate method triggered");
//            System.out.println("Username: " + userRequest.getUserName());
//            String token = userService.authenticateUser(userRequest.getUserName(), userRequest.getPassword());
//            return ResponseEntity.ok(token);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }
}
