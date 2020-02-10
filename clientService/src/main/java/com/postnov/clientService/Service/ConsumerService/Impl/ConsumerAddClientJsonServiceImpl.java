package com.postnov.clientService.Service.ConsumerService.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.clientService.Dto.LibraryCardsDto;
import com.postnov.clientService.Service.ConsumerService.ConsumerAddClientJsonService;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@EnableScheduling
public class ConsumerAddClientJsonServiceImpl implements ConsumerAddClientJsonService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerAddClientJsonServiceImpl.class);

    @Value("${queueName}")
    private String queueName;

    private final Channel channel;

    private final LibraryCardService libraryCardService;

    public ConsumerAddClientJsonServiceImpl(
            Channel channel,
            LibraryCardService libraryCardService) {
        this.channel = channel;
        this.libraryCardService = libraryCardService;
    }

    @Scheduled(fixedRate = 1000)
    @Override
    public void getJsonMessage() throws IOException, TimeoutException {

        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            logger.info(" [x] get message'" + message + "'");
            libraryCardService.saveLibraryCards(
                    mapFromJson("{\"libraryCardsDto\": " + message + "}", LibraryCardsDto.class).getLibraryCardsDto());
            logger.info(" [x] Request successful");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
