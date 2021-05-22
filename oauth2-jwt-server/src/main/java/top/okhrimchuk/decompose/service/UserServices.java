package top.okhrimchuk.decompose.service;

import top.okhrimchuk.decompose.domain.UserDTO;
import top.okhrimchuk.decompose.service.exeption.ServiceExeption;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserServices {
    List<User> getAll();
    int add(UserDTO student) throws ServiceExeption;
}
