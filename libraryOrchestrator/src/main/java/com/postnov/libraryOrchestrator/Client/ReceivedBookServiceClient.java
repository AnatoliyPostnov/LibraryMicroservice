package com.postnov.libraryOrchestrator.Client;

import com.postnov.libraryOrchestrator.Config.ClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ReceivedBookServiceClient", url = "http://localhost:8083", configuration = ClientConfig.class)
public interface ReceivedBookServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/received/books/filter")
    ResponseEntity<String> getReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

    @RequestMapping(method = RequestMethod.GET, value = "history/received/books/filter")
    ResponseEntity<String> getHistoryReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

    @RequestMapping(method = RequestMethod.GET, value = "all/received/books/filter")
    ResponseEntity<String> getAllReceivedBook(
            @RequestParam("fromReceivedBookId") Long fromReceivedBookId,
            @RequestParam("toReceivedBookId") Long toReceivedBookId);
}
