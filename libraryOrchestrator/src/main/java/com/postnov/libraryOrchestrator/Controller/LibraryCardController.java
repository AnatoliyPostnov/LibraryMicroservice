package com.postnov.libraryOrchestrator.Controller;

import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import com.postnov.libraryOrchestrator.Service.ProduserService.LibraryCardProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add/libraryCards")
    public void addLibraryCards(@RequestBody String addLibraryCardJson) {
        libraryCardProducerService.send(addLibraryCardJson);
    }

    @GetMapping("/libraryCard/filter")
    public ResponseEntity<String> getLibraryCardByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return libraryCardService.getLibraryCardDtoByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("/libraryCards/filter")
    public ResponseEntity<String> getLibraryCardsByFromLibraryCardsIdToLibraryCardsId(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId) {
        System.out.println(fromLibraryCardsId);
        return libraryCardService.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}