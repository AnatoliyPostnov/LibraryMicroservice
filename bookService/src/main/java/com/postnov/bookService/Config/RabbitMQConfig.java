package com.postnov.bookService.Config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class RabbitMQConfig {

    @Value("${host}")
    private String host;

    @Value("${queueName}")
    private String queueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        return factory;
    }

    @Bean
    public Connection connection(ConnectionFactory connectionFactory)
            throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    @Bean
    public Channel channel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(queueName, false, false, false, null);
        return channel;
    }
}
