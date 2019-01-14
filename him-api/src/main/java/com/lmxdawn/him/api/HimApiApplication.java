package com.lmxdawn.him.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HimApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HimApiApplication.class, args);
    }

}

