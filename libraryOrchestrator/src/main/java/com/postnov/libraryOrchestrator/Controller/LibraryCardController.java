package com.postnov.libraryOrchestrator.Controller;

import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import com.postnov.libraryOrchestrator.Service.ProduserService.LibraryCardProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    private final LibraryCardProducerService libraryCardProducerService;

    public LibraryCardController(
            LibraryCardService libraryCardService,
            LibraryCardProducerService libraryCardProducerService) {
        this.libraryCardService = libraryCardService;
        this.libraryCardProducerService = libraryCardProducerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/libraryCards")
    public void addLibraryCards(
            @RequestBody String addLibraryCardJson) {
        libraryCardProducerService.send(addLibraryCardJson);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCard/filter")
    public String getLibraryCardByPassportNumberAndSeries(
            @RequestParam("number") String number,
            @RequestParam("series") String series){
        return libraryCardService.getLibraryCardDtoByPassportNumberAndSeries(number, series);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/libraryCards/filter")
    public String getLibraryCardsByFromLibraryCardsIdToLibraryCardsId(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        System.out.println(fromLibraryCardsId);
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}