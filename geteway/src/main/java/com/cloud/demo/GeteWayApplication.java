package com.cloud.demo;

import com.cloud.demo.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GeteWayApplication {
    public static void main(String[] args) {
        SpringApplication.run( GeteWayApplication.class, args );
    }

    @Bean
    public AuthFilter tokenFilter(){
        return new AuthFilter();
    }
}
