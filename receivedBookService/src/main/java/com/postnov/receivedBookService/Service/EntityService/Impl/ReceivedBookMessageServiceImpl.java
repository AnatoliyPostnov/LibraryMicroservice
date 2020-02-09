package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.Entity.ReceivedBookMessage;
import com.postnov.receivedBookService.Repository.ReceivedBookMessageRepository;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookMessageService;
import org.springframework.stereotype.Service;

@Service
public class ReceivedBookMessageServiceImpl implements ReceivedBookMessageService {

    private final ReceivedBookMessageRepository receivedBookMessageRepository;

    public ReceivedBookMessageServiceImpl(ReceivedBookMessageRepository receivedBookMessageRepository) {
        this.receivedBookMessageRepository = receivedBookMessageRepository;
    }

    @Override
    public void saveReceivedBookMessage(String message) {
        receivedBookMessageRepository.save(new ReceivedBookMessage(message));
    }

}