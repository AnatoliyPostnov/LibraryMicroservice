package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ProducerSender;
import com.postnov.libraryOrchestrator.Service.ProduserService.ReceivedBookProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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
    public void send(String message) {
        producerSender.send(message, queueName, receivedBookService);
    }

    @Override
    public void sendReceivedBook(String receivedBook) {
        send(String.format("receivedBook %s", receivedBook));
    }

    @Override
    public void sendReturnBooks(String passportNumber, String passportSeries, String bookName) {
        send(String.format("returnBooks %s %s %s", passportNumber, passportSeries, bookName));
    }

    @Override
    public void sendDeleteLibraryCard(String passportNumber, String passportSeries) {
        send(String.format("deleteLibraryCard %s %s", passportNumber, passportSeries));
    }

    @Override
    public void sendDeletedBook(String bookName, Integer bookVolume) {
        send(String.format("deletedBook %s %s", bookName, bookVolume));
    }
}
