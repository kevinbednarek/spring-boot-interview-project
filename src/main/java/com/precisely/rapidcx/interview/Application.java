package com.precisely.rapidcx.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.precisely.rapidcx")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}