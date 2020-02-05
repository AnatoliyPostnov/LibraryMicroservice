package com.postnov.libraryOrchestrator.Service.EntityService;

public interface ReceivedBookService extends EntityService{

    void receivedBook(String receivedBook);

    void returnBooks(String passportNumber, String passportSeries, String booksName);

    void deleteLibraryCard(String passportNumber, String passportSeries);

    void deleteBookByBookNameAndVolume(String bookName, Integer volume);

    String getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    String getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    String getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId, Boolean forSendEmailClient);

}
