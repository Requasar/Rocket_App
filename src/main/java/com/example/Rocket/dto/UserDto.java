package com.example.Rocket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username; // Kullanıcı adı veya e-posta (benzersiz olmalı)
    private String password; // Şifre (hashed bir şekilde tutulmalı!)
    private String role; // Kullanıcı rolü (ADMIN, USER, vb.)
}
