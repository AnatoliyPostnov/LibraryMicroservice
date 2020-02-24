package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Service.EntityService.EntityService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ProducerSender;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerSenderImpl implements ProducerSender {

    private final Logger logger = LoggerFactory.getLogger(BookProducerServiceImpl.class);

    private final ConnectionFactory connectionFactory;

    public ProducerSenderImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public synchronized void send(String json, String queueName, EntityService entityService) {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, json.getBytes(StandardCharsets.UTF_8));
            logger.info(" [x] Sent '" + json + "'");
        } catch (TimeoutException | IOException e) {
            entityService.saveJsonInDB(json);
            e.printStackTrace();
        }
    }

}
