package com.postnov.libraryOrchestrator.Service.ProduserService;

public interface ReceivedBookProducerService extends EntityProducerSender {

    void sendReceivedBook (String receivedBook);

    void sendReturnBooks (String passportNumber, String passportSeries, String bookName);

    void sendDeleteLibraryCard(String passportNumber, String passportSeries);

    void sendDeletedBook (String bookName, Integer bookVolume);

}
