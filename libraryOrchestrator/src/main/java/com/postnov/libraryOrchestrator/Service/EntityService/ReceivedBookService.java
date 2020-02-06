package com.postnov.libraryOrchestrator.Service.EntityService;

public interface ReceivedBookService extends EntityService{

    String getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    String getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    String getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId);

}
