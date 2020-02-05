package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import com.postnov.libraryOrchestrator.Service.ProduserService.LibraryCardProducerService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ProducerSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardProducerServiceImpl implements LibraryCardProducerService {

    private final Logger logger = LoggerFactory.getLogger(BookProducerServiceImpl.class);

    @Value("${queueNameClientService}")
    private String queueName;

    private final LibraryCardService libraryCardService;

    private final ProducerSender producerSender;

    public LibraryCardProducerServiceImpl(
            LibraryCardService libraryCardService,
            ProducerSender producerSender) {
        this.libraryCardService = libraryCardService;
        this.producerSender = producerSender;
    }

    @Override
    public void send(String json) {
        producerSender.send(json, queueName, libraryCardService);
    }
}
