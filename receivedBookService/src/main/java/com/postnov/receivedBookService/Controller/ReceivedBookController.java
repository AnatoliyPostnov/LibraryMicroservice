package com.postnov.receivedBookService.Controller;

import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReceivedBookController {

    private final ReceivedBookService receivedBookService;

    public ReceivedBookController(
            ReceivedBookService receivedBookService) {
        this.receivedBookService = receivedBookService;
    }

    @GetMapping("/received/books/filter")
    public List<ReceivedBookDto> getReceivedBooksByPassportSNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("/history/received/books/filter")
    public List<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) {
        return receivedBookService
                .getHistoryReceivedBooksByPassportNumberAndSeries(passportNumber, passportSeries);
    }

    @GetMapping("/all/received/books/filter")
    public List<ReceivedBookDto> getAllReceivedBooks(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId) throws Exception {
        return receivedBookService
                .getAllReceivedBook(fromReceivedBookId, toReceivedBookId, false);
    }

    @GetMapping("/all/received/books/for/email/sender")
    public List<ReceivedBookDto> getAllReceivedBooksForEmailSender() throws Exception {
        return receivedBookService
                .getAllReceivedBook(null, null, true);
    }
}
