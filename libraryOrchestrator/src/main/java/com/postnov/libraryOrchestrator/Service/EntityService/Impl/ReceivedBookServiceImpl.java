package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Client.ReceivedBookServiceClient;
import com.postnov.libraryOrchestrator.Entity.Message;
import com.postnov.libraryOrchestrator.Entity.ReceivedBookMessage;
import com.postnov.libraryOrchestrator.Repository.ReceivedBookRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivedBookServiceImpl implements ReceivedBookService {

    private final ReceivedBookRepository receivedBookRepository;

    private final ReceivedBookServiceClient receivedBookServiceClient;

    public ReceivedBookServiceImpl(
            ReceivedBookRepository receivedBookRepository,
            ReceivedBookServiceClient receivedBookServiceClient) {
        this.receivedBookRepository = receivedBookRepository;
        this.receivedBookServiceClient = receivedBookServiceClient;
    }

    @Override
    public ResponseEntity<String> getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        return receivedBookServiceClient.getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @Override
    public ResponseEntity<String> getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        return receivedBookServiceClient.getHistoryReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @Override
    public ResponseEntity<String> getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId) {
        return receivedBookServiceClient.getAllReceivedBook(fromReceivedBookId, toReceivedBookId);
    }

    @Override
    public void saveJsonInDB(String json) {
        receivedBookRepository.save(new ReceivedBookMessage(json));
    }

    @Override
    public List<Message> getJson() {
        List<ReceivedBookMessage> receivedBookMessages = receivedBookRepository.findAll();
        return receivedBookMessages.stream().map(x -> (Message) x).collect(Collectors.toList());
    }

    @Override
    public void deleteJson(Message json) {
        receivedBookRepository.delete((ReceivedBookMessage) json);
    }
}
