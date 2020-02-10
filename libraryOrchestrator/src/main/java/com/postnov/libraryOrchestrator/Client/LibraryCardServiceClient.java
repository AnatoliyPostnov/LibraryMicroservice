package com.postnov.libraryOrchestrator.Client;

import com.postnov.libraryOrchestrator.Config.ClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "LibraryCardServiceClient", url = "http://localhost:8082", configuration = ClientConfig.class)
public interface LibraryCardServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/libraryCard/filter")
    ResponseEntity<String> getLibraryCardDtoByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

    @RequestMapping(method = RequestMethod.GET, value = "/libraryCards/filter")
    ResponseEntity<String> getLibraryCards(
            @RequestParam("fromLibraryCardsId") Long fromLibraryCardsId,
            @RequestParam("toLibraryCardsId") Long toLibraryCardId);
}
