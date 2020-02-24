package com.postnov.receivedBookService.Service.ConsumerSevice.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Service.ConsumerSevice.ConsumeService;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookMessageService;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {

    private final Logger logger = LoggerFactory.getLogger(ConsumeServiceImpl.class);

    private final ReceivedBookService receivedBookService;

    private final ReceivedBookMessageService receivedBookMessageService;

    @Value("${inputQueueName}")
    private String queueName;

    public ConsumeServiceImpl(
            ReceivedBookService receivedBookService,
            ReceivedBookMessageService receivedBookMessageService) {
        this.receivedBookService = receivedBookService;
        this.receivedBookMessageService = receivedBookMessageService;
    }

    @RabbitListener(queues = "#{getQueueName}")
    public void getJsonMessage(String message) throws IOException {
        logger.info(" [x] Consume '" + message + "'");
        parseMessage(message);
    }

    @Override
    public synchronized void parseMessage(String message) {
        List<String> messages = Arrays.asList(message.split("&"));
        try {
            switch (messages.get(0)) {
                case "receivedBook":
                    System.out.println(messages.get(1));
                    receivedBookService.receivedBook(mapFromJson(messages.get(1), ReceivedBookDto.class));
                    break;
                case "returnBooks":
                    receivedBookService.returnBooks(messages.get(1), messages.get(2), messages.get(3));
                    break;
                case "deleteLibraryCard":
                    receivedBookService.deleteLibraryCard(messages.get(1), messages.get(2));
                    break;
                case "deletedBook":
                    receivedBookService.deleteBookByBookNameAndVolume(messages.get(1), Integer.parseInt(messages.get(2)));
                    break;
                default:
                    throw new RuntimeException("Incorrect first param");
            }
        } catch (Exception e) {
            receivedBookMessageService.saveReceivedBookMessage(message);
            e.printStackTrace();
        }
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}
