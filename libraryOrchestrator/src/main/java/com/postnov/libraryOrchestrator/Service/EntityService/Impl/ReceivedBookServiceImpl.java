package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivedBookServiceImpl implements ReceivedBookService {

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
    public void saveJsonInDB(String Json) {

    }

    @Override
    public List<JsonMessage> getJson() {
        return null;
    }

    @Override
    public void deleteJson(JsonMessage json) {

    }
}
