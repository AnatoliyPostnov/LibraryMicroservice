package com.postnov.clientService.Exception.other;

import com.postnov.clientService.Dto.LibraryCardDto;

public class LibraryCardImpossibleSaveException extends RuntimeException {

    public LibraryCardImpossibleSaveException(LibraryCardDto libraryCardDto) {
        super("libraryCard: " + libraryCardDto.toString() + " already exist in database");
    }
}
