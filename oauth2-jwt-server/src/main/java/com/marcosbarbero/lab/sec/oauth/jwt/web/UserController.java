package com.marcosbarbero.lab.sec.oauth.jwt.web;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.UserDTO;
import com.marcosbarbero.lab.sec.oauth.jwt.service.UserServices;
import com.marcosbarbero.lab.sec.oauth.jwt.service.exeption.ServiceExeption;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServices userServices;

    @GetMapping("/me")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @ResponseBody
    @ApiOperation(value = "getAll User", response = User.class, responseContainer = "List")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userServices.getAll());
    }

    @ResponseBody
    @PostMapping(value = "/add")
    @ApiOperation(value = "add new User", response = UserDTO.class)
    public ResponseEntity add(@RequestBody UserDTO user){
        try {
            String result = "Succes save user.";
            String resultBad = "Unsucces save user.";
            return new ResponseEntity(userServices.add(user) == 1 ? result : resultBad, HttpStatus.CREATED);
        } catch (ServiceExeption ex) {
            log.error(ex.getMessage());
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
