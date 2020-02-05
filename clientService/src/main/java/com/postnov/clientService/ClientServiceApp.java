package com.postnov.clientService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ClientServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApp.class, args);
    }
}
