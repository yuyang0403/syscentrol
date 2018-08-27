package com.yuyang.other;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicserviceOtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicserviceOtherApplication.class, args);
    }
}
