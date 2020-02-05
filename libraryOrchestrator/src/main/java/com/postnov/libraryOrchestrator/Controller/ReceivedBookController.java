package com.postnov.libraryOrchestrator.Controller;

import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.ReceivedBookProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    private final ReceivedBookProducerService receivedBookProducerService;

    public ReceivedBookController(
            ReceivedBookService receivedBookService,
            ReceivedBookProducerService receivedBookProducerService) {
        this.receivedBookService = receivedBookService;
        this.receivedBookProducerService = receivedBookProducerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/received/book")
    public void receivedBook(
            @RequestBody String receivedBook) {
        receivedBookProducerService.sendReceivedBook(receivedBook);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/return/books/filter")
    public void returnBooksByBookName(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries,
            @RequestParam("booksName") String booksName) {
        receivedBookProducerService.sendReturnBooks(passportNumber, passportSeries, booksName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received/books/filter")
    public String getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history/received/books/filter")
    public String getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getHistoryReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/received/books/filter")
    public String getAllReceivedBooks(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId) throws Exception {
        return receivedBookService
                .getAllReceivedBook(fromReceivedBookId, toReceivedBookId, false);
    }

    @ResponseStatus(HttpStatus.OK)
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
