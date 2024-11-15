package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesingPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesingPatternApplication.class, args);
    }

}
