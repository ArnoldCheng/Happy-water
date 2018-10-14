package com.wh.demoregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DemoRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoRegisterApplication.class, args);
    }
}
