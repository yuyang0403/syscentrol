package com.yuyang.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy
@SpringCloudApplication
public class MicserviceZuul {

    public static void main(String[] args) {
        SpringApplication.run(MicserviceZuul.class, args);
    }

    @Bean
    public ErrorFilter getErrorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public PreZuulFilter getPreError() {
        return new PreZuulFilter();
    }

    @Bean
    public PostZuulFilter getPostError() {
        return new PostZuulFilter();
    }
}
