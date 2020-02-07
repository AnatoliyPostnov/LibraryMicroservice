package com.postnov.receivedBookService.Service.EntityService;

import com.postnov.receivedBookService.Dto.LibraryCardDto;

public interface LibraryCardService {

    LibraryCardDto getLibraryCardDtoById(Long Id);

    Long getLibraryCardIdByPassportNumberAndSeries(String number, String series);

    void deleteLibraryCard(String number, String series);

}