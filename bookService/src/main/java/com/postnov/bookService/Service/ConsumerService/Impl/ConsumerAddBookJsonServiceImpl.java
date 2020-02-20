package com.postnov.bookService.Service.ConsumerService.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.bookService.Dto.SetBookDto;
import com.postnov.bookService.Service.ConsumerService.ConsumerAddBookJsonService;
import com.postnov.bookService.Service.EntityService.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerAddBookJsonServiceImpl implements ConsumerAddBookJsonService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerAddBookJsonServiceImpl.class);

    private final BookService bookService;

    public ConsumerAddBookJsonServiceImpl(
            BookService bookService) {
        this.bookService = bookService;
    }

    @RabbitListener(queues = "#{getQueueName}")
    public void getJsonMessage(String message) throws IOException {
        logger.info(" [x] Consume '" + message + "'");
        bookService.saveBooks(
                mapFromJson("{\"booksDto\": " + message + "}", SetBookDto.class).getBooksDto());
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
