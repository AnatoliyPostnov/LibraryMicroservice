package com.postnov.emailSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmailSenderApp {

    public static void main(String[] args) {
        SpringApplication.run(EmailSenderApp.class, args);
    }
}