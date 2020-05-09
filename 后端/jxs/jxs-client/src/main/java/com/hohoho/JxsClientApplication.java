package com.hohoho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JxsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(JxsClientApplication.class, args);
    }

}
