package com.postnov.receivedBookService.Controller;

import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    public ReceivedBookController(
            ReceivedBookService receivedBookService) {
        this.receivedBookService = receivedBookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received/books/filter")
    public List<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history/received/books/filter")
    public List<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getHistoryReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all/received/books/filter")
    public List<ReceivedBookDto> getAllReceivedBooks(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId) throws Exception {
        return receivedBookService
                .getAllReceivedBook(fromReceivedBookId, toReceivedBookId, false);
    }
}
