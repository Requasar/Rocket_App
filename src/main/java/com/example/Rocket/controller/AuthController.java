package com.example.Rocket.controller;

import com.example.Rocket.dto.UserDto;
import com.example.Rocket.requests.UserRequest;
import com.example.Rocket.responses.AuthResponse;
import com.example.Rocket.security.JwtTokenProvider;
import com.example.Rocket.service.UserDetailService;
import com.example.Rocket.service.UserService;
import com.example.Rocket.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

//    private AuthenticationManager authenticationManager;
//
//    private JwtTokenProvider jwtTokenProvider;
//
//    private UserServiceImpl userServiceImpl;
//
//    private PasswordEncoder passwordEncoder;
//
//    private UserDetailService userDetailService;
//
//    private UserService userService;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userServiceImpl = userServiceImpl;
//        this.passwordEncoder = passwordEncoder;
//        this.userDetailService = userDetailService;
//        this.userService = userService;
//    }
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody UserRequest loginRequest) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
//        Authentication auth = authenticationManager.authenticate(authToken);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
//        UserDto user = userService.getUserByUsername(loginRequest.getUserName());
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setMessage("User logged in successfully");
//        authResponse.setUserId(user.getId());
//        return authResponse;
//    }
//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest){
//        AuthResponse authResponse = new AuthResponse();
//        if(userDetailService.loadUserByUsername(registerRequest.getUserName()) != null) {
//            authResponse.setMessage("Username already exists");
//            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
//        }
//        UserDto userDto = new UserDto();
//        userDto.setUsername(registerRequest.getUserName());
//        userDto.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        userService.createUser(userDto);
//        authResponse.setMessage("User registered successfully");
//        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
//    }
}