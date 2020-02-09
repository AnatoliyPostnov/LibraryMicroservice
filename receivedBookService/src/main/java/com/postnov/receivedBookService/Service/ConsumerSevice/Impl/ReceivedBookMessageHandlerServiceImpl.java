package com.postnov.receivedBookService.Service.ConsumerSevice.Impl;

import com.postnov.receivedBookService.Entity.ReceivedBookMessage;
import com.postnov.receivedBookService.Repository.ReceivedBookMessageRepository;
import com.postnov.receivedBookService.Service.ConsumerSevice.ConsumeService;
import com.postnov.receivedBookService.Service.ConsumerSevice.ReceivedBookMessageHandlerService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ReceivedBookMessageHandlerServiceImpl implements ReceivedBookMessageHandlerService {

    private final ReceivedBookMessageRepository receivedBookMessageRepository;

    private final ConsumeService consumeService;

    public ReceivedBookMessageHandlerServiceImpl(
            ReceivedBookMessageRepository receivedBookMessageRepository,
            ConsumeService consumeService) {
        this.receivedBookMessageRepository = receivedBookMessageRepository;
        this.consumeService = consumeService;
    }

    @Scheduled(fixedRate = 15000)
    public void handleReceivedBookMessage() {
        for (ReceivedBookMessage receivedBookMessage : receivedBookMessageRepository.findAll()) {
            consumeService.parseMessage(receivedBookMessage.getMessage());
            receivedBookMessageRepository.delete(receivedBookMessage);
        }
    }
}
