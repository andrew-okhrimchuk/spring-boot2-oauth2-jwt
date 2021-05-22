package com.marcosbarbero.lab.sec.oauth.jwt.service;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.UserDTO;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService implements UserServices {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    String SELECT_ALL = "SELECT * FROM USERS";
    String INSERT_USER = "INSERT INTO USERS (username, password, enabled) VALUES (?, ?, ?)";

    public UserService(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        List<User> users = jdbcTemplate.query(
                SELECT_ALL,
                (rs, rowNum) -> (User)User.builder().username(rs.getString("username")).password(rs.getString("password")).authorities(rs.getString("enabled")).build());
        System.out.println("users = " + users);
        return users;
    }

    @Override
    public int add(UserDTO user) {
        return jdbcTemplate.update(INSERT_USER,  user.getUsername(), passwordEncoder.encode(user.getPassword()), 1);
    }
}
