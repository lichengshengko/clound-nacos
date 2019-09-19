package com.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cloud.demo")
public class OrderCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderCustomerApplication.class, args);
    }
}
