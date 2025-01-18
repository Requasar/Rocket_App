package com.example.Rocket.service;

import com.example.Rocket.dto.UserDto;
import com.example.Rocket.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userdto);

    UserDto getUserById(Long userId);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long userId, UserDto updatedUser);

    String authenticateUser(String username, String password) throws Exception;

    void deleteUser(Long userId);
}
