package com.example.Rocket.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String username; // Kullanıcı adı veya e-posta (benzersiz olmalı)
    private String password; // Şifre (hashed bir şekilde tutulmalı!)
    private String role; // Kullanıcı rolü (ADMIN, USER, vb.)
}
