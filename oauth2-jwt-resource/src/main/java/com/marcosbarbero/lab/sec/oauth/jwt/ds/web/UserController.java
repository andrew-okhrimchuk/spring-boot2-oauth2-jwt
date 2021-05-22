package com.marcosbarbero.lab.sec.oauth.jwt.ds.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
@Slf4j
@RestController
@RequestMapping("/me")
public class UserController {


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STUDENT')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/all")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Principal> get2(final Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/all3")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String currentUserNameSimple(HttpServletRequest request) {
        log.debug("request getRemoteUser = " + request.getRemoteUser());
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

}
