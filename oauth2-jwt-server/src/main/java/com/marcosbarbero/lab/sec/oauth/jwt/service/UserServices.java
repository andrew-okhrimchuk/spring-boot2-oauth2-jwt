package com.marcosbarbero.lab.sec.oauth.jwt.service;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.UserDTO;
import com.marcosbarbero.lab.sec.oauth.jwt.service.exeption.ServiceExeption;
import org.springframework.security.core.userdetails.User;
import java.util.List;

public interface UserServices {
    List<User> getAll();
    int add(UserDTO student) throws ServiceExeption;
}
