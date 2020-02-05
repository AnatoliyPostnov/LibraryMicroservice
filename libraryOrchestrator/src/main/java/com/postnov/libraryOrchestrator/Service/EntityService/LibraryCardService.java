package com.postnov.libraryOrchestrator.Service.EntityService;

public interface LibraryCardService extends EntityService {

    String getLibraryCardDtoByPassportNumberAndSeries(String number, String series);

    String getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

}
