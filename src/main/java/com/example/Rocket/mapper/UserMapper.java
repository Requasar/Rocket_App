package com.example.Rocket.mapper;

import com.example.Rocket.dto.RocketDto;
import com.example.Rocket.dto.UserDto;
import com.example.Rocket.entity.Rocket;
import com.example.Rocket.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
    public static User mapToUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }
}
