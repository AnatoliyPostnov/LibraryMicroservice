package com.postnov.bookService.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${queueName}")
    private String queueName;

    @Bean
    public String getQueueName() {
        return queueName;
    }

}
