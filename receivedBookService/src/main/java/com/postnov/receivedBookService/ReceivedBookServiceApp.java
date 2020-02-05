package com.postnov.receivedBookService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ReceivedBookServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ReceivedBookServiceApp.class, args);
    }
}
