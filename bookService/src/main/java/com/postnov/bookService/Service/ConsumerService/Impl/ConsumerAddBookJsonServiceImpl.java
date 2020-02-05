package com.postnov.bookService.Service.ConsumerService.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.bookService.Dto.SetBookDto;
import com.postnov.bookService.Service.ConsumerService.ConsumerAddBookJsonService;
import com.postnov.bookService.Service.EntityService.BookService;
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
public class ConsumerAddBookJsonServiceImpl implements ConsumerAddBookJsonService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerAddBookJsonServiceImpl.class);

    @Value("${queueName}")
    private String queueName;

    private final Channel channel;

    private final BookService bookService;

    public ConsumerAddBookJsonServiceImpl(
            Channel channel,
            BookService bookService) {
        this.channel = channel;
        this.bookService = bookService;
    }

    @Scheduled(fixedRate = 1000)
    @Override
    public void getJsonMessage() throws IOException, TimeoutException {

        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            logger.info(" [x] get message'" + message + "'");
            bookService.saveBooks(
                    mapFromJson("{\"booksDto\": " + message + "}", SetBookDto.class).getBooksDto());
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
