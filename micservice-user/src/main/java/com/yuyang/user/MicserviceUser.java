package com.yuyang.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@SpringCloudApplication
@MapperScan("com.yuyang.user.mapper")
public class MicserviceUser {
    public static void main(String[] args) {
        SpringApplication.run(MicserviceUser.class, args);
    }
}
