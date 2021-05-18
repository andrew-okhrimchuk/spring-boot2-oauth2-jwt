package com.marcosbarbero.lab.sec.oauth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OAuth2ServerJwtApplication {

    public static void main(String... args) {
        SpringApplication.run(OAuth2ServerJwtApplication.class, args);
    }
}
