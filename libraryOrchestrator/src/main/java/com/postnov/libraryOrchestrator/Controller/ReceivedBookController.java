package com.postnov.libraryOrchestrator.Controller;

import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ReceivedBookProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    private final ReceivedBookProducerService receivedBookProducerService;

    public ReceivedBookController(
            ReceivedBookService receivedBookService,
            ReceivedBookProducerService receivedBookProducerService) {
        this.receivedBookService = receivedBookService;
        this.receivedBookProducerService = receivedBookProducerService;
    }

    @PostMapping("/received/book")
    public void receivedBook(
            @RequestBody String receivedBookJson) {
        receivedBookProducerService.sendReceivedBook(receivedBookJson);
    }

    @PostMapping("/return/book/filter")
    public void returnBooksByBookName(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries,
            @RequestParam("booksName") String booksName) {
        receivedBookProducerService.sendReturnBooks(passportNumber, passportSeries, booksName);
    }

    @GetMapping("/received/books/filter")
    public ResponseEntity<String> getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService.getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("/history/received/books/filter")
    public ResponseEntity<String> getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService.getHistoryReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("all/received/books/filter")
    public ResponseEntity<String> getAllReceivedBooks(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId) {
        return receivedBookService.getAllReceivedBook(fromReceivedBookId, toReceivedBookId);
    }

    @DeleteMapping("/libraryCard/filter")
    public void deleteLibraryCardByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        receivedBookProducerService.sendDeleteLibraryCard(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/book/filter")
    public void deletedBookByBookNameAndVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        receivedBookProducerService.sendDeletedBook(bookName, bookVolume);
    }
}
