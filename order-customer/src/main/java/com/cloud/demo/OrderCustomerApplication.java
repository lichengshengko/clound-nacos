package com.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cloud.demo")
@MapperScan("com.cloud.demo.mapper")
public class OrderCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderCustomerApplication.class, args);
    }
}
