package com.yuyang.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicserviceEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicserviceEurekaServerApplication.class, args);
    }
}
