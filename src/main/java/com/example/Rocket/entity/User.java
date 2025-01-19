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
    private String username;
    private String password;
    private String role;
}
