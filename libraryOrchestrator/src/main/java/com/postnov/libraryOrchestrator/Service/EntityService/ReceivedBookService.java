package com.postnov.libraryOrchestrator.Service.EntityService;

import org.springframework.http.ResponseEntity;

public interface ReceivedBookService extends EntityService {

    ResponseEntity<String> getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    ResponseEntity<String> getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    ResponseEntity<String> getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId);

}
