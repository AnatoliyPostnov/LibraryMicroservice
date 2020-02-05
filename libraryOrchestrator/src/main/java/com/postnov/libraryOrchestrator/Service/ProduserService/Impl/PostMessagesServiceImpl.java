package com.postnov.libraryOrchestrator.Service.ProduserService.Impl;

import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Service.EntityService.BookService;
import com.postnov.libraryOrchestrator.Service.EntityService.EntityService;
import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.BookProducerService;
import com.postnov.libraryOrchestrator.Service.ProduserService.EntityProducerSender;
import com.postnov.libraryOrchestrator.Service.ProduserService.LibraryCardProducerService;
import com.postnov.libraryOrchestrator.Service.ProduserService.PostMessagesService;
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

    private final LibraryCardProducerService libraryCardProducerService;

    private final BookProducerService bookProducerService;

    public PostMessagesServiceImpl(
            LibraryCardService libraryCardService,
            BookService bookService,
            ReceivedBookService receivedBookService,
            LibraryCardProducerService libraryCardProducerService,
            BookProducerService bookProducerService) {
        this.libraryCardService = libraryCardService;
        this.bookService = bookService;
        this.receivedBookService = receivedBookService;
        this.libraryCardProducerService = libraryCardProducerService;
        this.bookProducerService = bookProducerService;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void postMessages() {
        post(bookService, bookProducerService);
        post(libraryCardService, libraryCardProducerService);
    }

    private void post(EntityService entityService, EntityProducerSender producerSender) {
        List<JsonMessage> entities = entityService.getJson();
        for (JsonMessage entity : entities) {
            entityService.deleteJson(entity);
            producerSender.send(entity.getMessage());
        }
    }
}
