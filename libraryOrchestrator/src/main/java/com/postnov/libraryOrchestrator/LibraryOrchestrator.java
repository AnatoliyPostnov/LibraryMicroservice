package com.postnov.libraryOrchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LibraryOrchestrator {

    public static void main(String[] args) {
        SpringApplication.run(LibraryOrchestrator.class, args);
    }
}
