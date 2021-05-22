package top.okhrimchuk.decompose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ResourceServerJwtApplication {
    public static void main(String... args) {
        SpringApplication.run(ResourceServerJwtApplication.class, args);
    }
}
