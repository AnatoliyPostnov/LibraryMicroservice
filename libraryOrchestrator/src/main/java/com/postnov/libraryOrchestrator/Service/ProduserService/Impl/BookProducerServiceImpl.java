package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Service.EntityService.BookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.BookProducerService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ProducerSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookProducerServiceImpl implements BookProducerService {

    @Value("${queueNameBookService}")
    private String queueName;

    private final BookService bookService;

    private final ProducerSender producerSender;

    public BookProducerServiceImpl(
            BookService bookService,
            ProducerSender producerSender) {
        this.bookService = bookService;
        this.producerSender = producerSender;
    }

    @Override
    public void send(String json) {
        producerSender.send(json, queueName, bookService);
    }
}