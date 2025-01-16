package com.example.Rocket.repository;

import com.example.Rocket.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Repository
public class UserRepository {
    private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcClient jdbcClient;

    public UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<User> findAll() {
        return jdbcClient.sql("select * from rocket1.users")
                .query(User.class)
                .list();
    }

    public Optional<User> findById(Long id) {
        return jdbcClient.sql("SELECT id,username,password,role FROM rocket1.users WHERE id = :id" )
                .param("id", id)
                .query(User.class)
                .optional();
    }

    public Optional<User> findByUsername(String username) {
        return jdbcClient.sql("SELECT id,username,password,role FROM rocket1.users WHERE username = :username" )
                .param("username", username)
                .query(User.class)
                .optional();
    }

//    public void create(User user) {
//        // To add database
//        var updated = jdbcClient.sql("INSERT INTO rocket1.users (username, password, role) VALUES (?, ?, ?)")
//                .params(List.of(
//                        user.getUsername(),
//                        user.getPassword(),
//                        user.getRole()
//
//                ))
//                .update();
//        Assert.state(updated == 1, "Failed to create User " + user.getUsername());
//    }

    private BCryptPasswordEncoder passwordEncoder;

    public void create(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        // To add database
        var updated = jdbcClient.sql("INSERT INTO rocket1.users (username, password, role) VALUES (?, ?, ?)")
                .params(List.of(
                        user.getUsername(),
                        hashedPassword,
                        user.getRole()

                ))
                .update();
        Assert.state(updated == 1, "Failed to create User " + user.getUsername());
    }


    public void update(User user, Long id) {
        var updated = jdbcClient.sql("UPDATE rocket1.users " +
                        "SET username = ?,  password = ?, role = ? " +
                        "WHERE id = ?")
                .params(List.of(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRole(),
                        id
                ))
                .update();

        log.info("Update operation result: {}", updated);

        Assert.state(updated == 1, "Failed to update User " + user.getUsername() + " (id: " + id + ")");
    }


    public void delete(Long id) {
        var updated = jdbcClient.sql("DELETE FROM rocket1.users WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete User with id " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from rocket1.users").query().listOfRows().size();
    }

    public void saveAll(List<User> runs) { //that can take a list of users and insert
        runs.stream().forEach(this::create);
    }


}