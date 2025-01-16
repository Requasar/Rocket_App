package com.example.Rocket.service.impl;

import com.example.Rocket.dto.UserDto;
import com.example.Rocket.entity.User;
import com.example.Rocket.mapper.UserMapper;
import com.example.Rocket.repository.UserRepository;
import com.example.Rocket.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //spring container to create a spring bean for UserServiceImpl class
@AllArgsConstructor //constructor with all arguments
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
 //   private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        userRepository.create(user);
        UserDto createdUserDto = UserMapper.mapToUserDto(user);
        return createdUserDto;
    }


//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
//        user.setPassword(passwordEncoder.encode(user.getPassword())); // Şifreyi burada encode et
//        userRepository.create(user);
//        return UserMapper.mapToUserDto(user);
//    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    @Override
    public UserDto getUserByUsername(String userUsername) {
        Optional<User> userOptional = userRepository.findByUsername(userUsername);
        return userOptional
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userUsername));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        User existingUser = existingUserOptional
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        User updatedUserEntity = UserMapper.mapToUser(updatedUser);
        existingUser.setUsername(updatedUserEntity.getUsername());
        existingUser.setPassword(updatedUserEntity.getPassword());
        existingUser.setRole(updatedUserEntity.getRole());

        userRepository.update(existingUser, existingUser.getId());

        return UserMapper.mapToUserDto(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        User existingUser = existingUserOptional
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userRepository.delete(userId);
        log.info("User with id " + userId + " has been deleted.");
    }

}
