package com.postnov.clientService.Service.EntityService;

import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Entity.LibraryCard;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface LibraryCardService {

    void saveLibraryCards(List<LibraryCardDto> libraryCardsDto);

    void deleteLibraryCard(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Map<String, Object> getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    LibraryCardDto getLibraryCardDtoById(Long id) throws Exception;

    Optional<LibraryCard> save(LibraryCardDto libraryCardDto);

    LibraryCard getLibraryCardByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    LibraryCard getLibraryCardByClientId(Long clientId);

    LibraryCard getLibraryCardById(Long id) throws Exception;

    Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId);

    Map<String, Object> getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
