package com.postnov.clientService.Dto;

import java.util.ArrayList;
import java.util.List;

public class LibraryCardsDto {

    private List<LibraryCardDto> libraryCardsDto = new ArrayList<>();

    public List<LibraryCardDto> getLibraryCardsDto() {
        return libraryCardsDto;
    }

    public void setLibraryCardsDto(List<LibraryCardDto> libraryCardsDtoList) {
        this.libraryCardsDto = libraryCardsDtoList;
    }

}
