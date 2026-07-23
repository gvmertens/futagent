package com.futagend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FutagendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutagendApplication.class, args);
    }
}

