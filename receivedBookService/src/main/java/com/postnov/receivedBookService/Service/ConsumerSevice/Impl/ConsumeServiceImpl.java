package com.postnov.receivedBookService.Service.ConsumerSevice.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Service.ConsumerSevice.ConsumeService;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@EnableScheduling
public class ConsumeServiceImpl implements ConsumeService {

    private final Logger logger = LoggerFactory.getLogger(ConsumeServiceImpl.class);

    private final Channel channel;

    private final ReceivedBookService receivedBookService;

    @Value("${inputQueueName}")
    private String queueName;

    public ConsumeServiceImpl(
            Channel channel,
            ReceivedBookService receivedBookService) {
        this.channel = channel;
        this.receivedBookService = receivedBookService;
    }

    @Scheduled(fixedRate = 1000)
    private void getMessage() {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            parseMessage(message);
        };
        try {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
            });
        }catch (IOException e){
            logger.error(" [*] You catch IOException in Scheduled getMessage");
            e.printStackTrace();
        }
    }

    @Override
    public void parseMessage(String message) {
        List<String> messages = Arrays.asList(message.split("&"));
        try {
            switch (messages.get(0)) {
                case "receivedBook":
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
            receivedBookService.saveReceivedBookMessage(message);
            e.printStackTrace();
        }
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);

    }
}
