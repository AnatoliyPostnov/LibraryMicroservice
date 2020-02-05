package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Entity.ReceivedBookMessage;
import com.postnov.libraryOrchestrator.Repository.ReceivedBookRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivedBookServiceImpl implements ReceivedBookService {

    private final ReceivedBookRepository receivedBookRepository;

    public ReceivedBookServiceImpl(ReceivedBookRepository receivedBookRepository) {
        this.receivedBookRepository = receivedBookRepository;
    }

    @Override
    public void receivedBook(String receivedBook) {

    }

    @Override
    public void returnBooks(String passportNumber, String passportSeries, String booksName) {

    }

    @Override
    public void deleteLibraryCard(String passportNumber, String passportSeries) {

    }

    @Override
    public void deleteBookByBookNameAndVolume(String bookName, Integer volume) {

    }

    @Override
    public String getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        return null;
    }

    @Override
    public String getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        return null;
    }

    @Override
    public String getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId, Boolean forSendEmailClient) {
        return null;
    }

    @Override
    public void saveJsonInDB(String json) {
        receivedBookRepository.save(new ReceivedBookMessage(json));
    }

    @Override
    public List<JsonMessage> getJson() {
        List<ReceivedBookMessage> receivedBookMessages = receivedBookRepository.findAll();
        return receivedBookMessages.stream().map(x -> (JsonMessage) x).collect(Collectors.toList());
    }

    @Override
    public void deleteJson(JsonMessage json) {
        receivedBookRepository.delete((ReceivedBookMessage) json);
    }
}
