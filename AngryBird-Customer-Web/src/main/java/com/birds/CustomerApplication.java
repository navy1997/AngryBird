package com.birds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class CustomerApplication {

    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(CustomerApplication.class,args);
    }
}
