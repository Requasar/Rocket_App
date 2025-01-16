package com.example.Rocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(origins = "http://localhost:3000") // Frontend adresinizi buraya yazın
@Controller
public class LoginController {

    @PostMapping("/loginnnnn") // Frontend'in gönderdiği username ve password'u almak için
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password) {
        // Spring Security, doğrulamayı otomatik olarak yapacak.
        // Bu yöntem sadece frontend ile iletişimi sağlar.
        System.out.println("Username: " + username + ", Password: " + password);
        return "Login attempt received. Check Spring Security for authentication.";
    }
}
