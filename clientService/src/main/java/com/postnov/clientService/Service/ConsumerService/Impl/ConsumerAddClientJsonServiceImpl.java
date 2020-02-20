package com.postnov.clientService.Service.ConsumerService.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.clientService.Dto.LibraryCardsDto;
import com.postnov.clientService.Service.ConsumerService.ConsumerAddClientJsonService;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerAddClientJsonServiceImpl implements ConsumerAddClientJsonService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerAddClientJsonServiceImpl.class);

    private final LibraryCardService libraryCardService;

    public ConsumerAddClientJsonServiceImpl(
            LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @RabbitListener(queues = "#{getQueueName}")
    public void getJsonMessage(String message) throws IOException {
        logger.info(" [x] Consume '" + message + "'");
        libraryCardService.saveLibraryCards(
                mapFromJson("{\"libraryCardsDto\": " + message + "}", LibraryCardsDto.class).getLibraryCardsDto());
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
