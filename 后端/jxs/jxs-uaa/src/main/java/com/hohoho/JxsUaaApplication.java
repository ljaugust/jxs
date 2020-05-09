package com.hohoho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JxsUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxsUaaApplication.class, args);
    }

}
