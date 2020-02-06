package com.postnov.receivedBookService.Service.ConsumerSevice.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
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
import java.util.concurrent.TimeoutException;

@Service
@EnableScheduling
public class ConsumeServiceImpl implements ConsumeService {

    private final Logger logger = LoggerFactory.getLogger(ConsumeServiceImpl.class);

    @Value("${inputQueueName}")
    private String queueName;

    private final Channel channel;

    private final ReceivedBookService receivedBookService;

    public ConsumeServiceImpl(
            Channel channel,
            ReceivedBookService receivedBookService) {
        this.channel = channel;
        this.receivedBookService = receivedBookService;
    }

    @Scheduled(fixedRate = 1000)
    @Override
    public void getMessage() throws IOException, TimeoutException {
        logger.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            logger.info(" [x] get message'" + message + "'");
            parseMessage(message);
            logger.info(" [x] Request successful");
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

    private void parseMessage(String message) throws IOException {
        List<String> messages = Arrays.asList(message.split("&"));

        switch (messages.get(0)) {
            case "receivedBook":
                try {
                    receivedBookService.receivedBook(mapFromJson(messages.get(1), ReceivedBookDto.class));
                }catch (FindPassportByPassportNumberAndSeriesWasNotFoundException e){
                    logger.info(" [x] passport was not found " + e);
                }catch (Exception e){
                    receivedBookService.saveReceivedBookMessage(message);
                }
                break;
            case "returnBooks":
//            String passportNumber, String passportSeries, String bookName
                break;
            case "deleteLibraryCard":
//            String passportNumber, String passportSeries
                break;
            case "deletedBook":
//            String bookName, Integer bookVolume
                break;
            default:
                throw new RuntimeException("Incorrect first param");
        }
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);

    }
}
