package com.marcosbarbero.lab.sec.oauth.jwt.ds.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/me")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Principal> get2(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
