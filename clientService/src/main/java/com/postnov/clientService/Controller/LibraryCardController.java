package com.postnov.clientService.Controller;

import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCard/filter")
    public LibraryCardDto getLibraryCardByPassportNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return (LibraryCardDto) libraryCardService
                .getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(number, series)
                .get("LibraryCardDto");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCards/filter")
    public Set<LibraryCardDto> getLibraryCardsByFromLibraryCardsIdToLibraryCardsId(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}