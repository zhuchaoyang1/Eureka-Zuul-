package com.zcy.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurkaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurkaProducerApplication.class, args);
    }

}
