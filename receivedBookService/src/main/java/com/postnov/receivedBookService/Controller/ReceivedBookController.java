package com.postnov.receivedBookService.Controller;

import com.postnov.receivedBookService.Service.ReceivedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    public ReceivedBookController(ReceivedBookService receivedBookService) {
        this.receivedBookService = receivedBookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/received/book")
    public void receivedBook(
            @RequestBody ReceivedBookDto receivedBook){
        receivedBookService.receivedBook(receivedBook);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/return/books/filter")
    public void returnBooksByBookName(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries,
            @RequestParam("booksName") String booksName){
        receivedBookService.returnBooks(passportNumber, passportSeries, booksName);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received/books/filter")
    public String getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries){
        return receivedBookService
                .getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history/received/books/filter")
    public String getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries){
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
            @RequestParam("passportSeries") String passportSeries){
        receivedBookService.deleteLibraryCard(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/book/filter")
    public void deletedBookByBookNameAndVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        receivedBookService.deleteBookByBookNameAndVolume(bookName, bookVolume);
    }
}
