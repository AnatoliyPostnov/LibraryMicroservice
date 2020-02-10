package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Entity.Message;
import com.postnov.libraryOrchestrator.Service.EntityService.BookService;
import com.postnov.libraryOrchestrator.Service.EntityService.EntityService;
import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class PostMessagesServiceImpl implements PostMessagesService {

    private final LibraryCardService libraryCardService;

    private final BookService bookService;

    private final ReceivedBookService receivedBookService;

    private final ReceivedBookProducerService receivedBookProducerService;

    private final LibraryCardProducerService libraryCardProducerService;

    private final BookProducerService bookProducerService;

    public PostMessagesServiceImpl(
            LibraryCardService libraryCardService,
            BookService bookService,
            ReceivedBookService receivedBookService,
            ReceivedBookProducerService receivedBookProducerService,
            LibraryCardProducerService libraryCardProducerService,
            BookProducerService bookProducerService) {
        this.libraryCardService = libraryCardService;
        this.bookService = bookService;
        this.receivedBookService = receivedBookService;
        this.receivedBookProducerService = receivedBookProducerService;
        this.libraryCardProducerService = libraryCardProducerService;
        this.bookProducerService = bookProducerService;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void postMessages() {
        post(bookService, bookProducerService);
        post(libraryCardService, libraryCardProducerService);
        post(receivedBookService, receivedBookProducerService);
    }

    private void post(EntityService entityService, EntityProducerSender producerSender) {
        List<Message> entities = entityService.getJson();
        for (Message entity : entities) {
            entityService.deleteJson(entity);
            producerSender.send(entity.getMessage());
        }
    }
}
