package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ProducerSender;
import com.postnov.libraryOrchestrator.Service.ProduserService.ReceivedBookProducerService;
import org.springframework.beans.factory.annotation.Value;

public class ReceivedBookProducerServiceImpl implements ReceivedBookProducerService {

    @Value("${queueNameReceivedBookService}")
    private String queueName;

    private final ReceivedBookService receivedBookService;

    private final ProducerSender producerSender;

    public ReceivedBookProducerServiceImpl(
            ReceivedBookService receivedBookService,
            ProducerSender producerSender) {
        this.receivedBookService = receivedBookService;
        this.producerSender = producerSender;
    }

    @Override
    public void send(String json) {
        producerSender.send(json, queueName, receivedBookService);
    }
}
