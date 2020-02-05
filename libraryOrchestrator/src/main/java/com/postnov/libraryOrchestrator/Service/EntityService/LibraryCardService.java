package com.postnov.libraryOrchestrator.Service.EntityService;

import com.postnov.libraryOrchestrator.Entity.AddLibraryCardJson;

import java.util.List;

public interface LibraryCardService extends EntityService {

    String getLibraryCardDtoByPassportNumberAndSeries(String number, String series);

    String getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

}
