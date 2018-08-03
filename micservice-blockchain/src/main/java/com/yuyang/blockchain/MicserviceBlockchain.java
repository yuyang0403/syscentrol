package com.yuyang.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@SpringCloudApplication
public class MicserviceBlockchain {

    public static void main(String[] args) {
        SpringApplication.run(MicserviceBlockchain.class, args);
    }
}
