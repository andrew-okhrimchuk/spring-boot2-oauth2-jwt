package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

class DefaultPasswordEncoderFactories {

    @Bean
    static PasswordEncoder createDelegatingPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
