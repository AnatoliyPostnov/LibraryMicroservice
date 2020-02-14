package com.postnov.clientService.Controller;

import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping("/libraryCard/filter")
    public LibraryCardDto getLibraryCardByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return libraryCardService.getLibraryCardDtoByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("/libraryCards/filter")
    public List<LibraryCardDto> getLibraryCardsByFromLibraryCardsIdToLibraryCardsId(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}