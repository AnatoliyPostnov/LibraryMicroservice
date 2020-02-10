package com.postnov.libraryOrchestrator.Service.EntityService;

import org.springframework.http.ResponseEntity;

public interface LibraryCardService extends EntityService {

    ResponseEntity<String> getLibraryCardDtoByPassportNumberAndSeries(String number, String series);

    ResponseEntity<String> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

}
