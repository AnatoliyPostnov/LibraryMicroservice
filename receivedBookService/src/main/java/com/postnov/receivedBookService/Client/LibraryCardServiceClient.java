package com.postnov.receivedBookService.Client;

import com.postnov.receivedBookService.Config.ClientConfig;
import com.postnov.receivedBookService.Dto.LibraryCardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "LibraryCardServiceClient", url = "http://localhost:8082", configuration = ClientConfig.class)
public interface LibraryCardServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/filter")
    LibraryCardDto getLibraryCardDtoById(@RequestParam("Id") Long Id);

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/get/Id")
    Long getLibraryCardIdByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

    @RequestMapping(method = RequestMethod.DELETE, value = "CommunicateWithReceivedBook/delete/filter")
    void deleteLibraryCard(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

}