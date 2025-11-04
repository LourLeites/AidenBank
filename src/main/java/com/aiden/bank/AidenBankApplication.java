package com.aiden.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AidenBankApplication {
    public static void main(String[] args) {
        SpringApplication.run(AidenBankApplication.class, args);
    }
}
