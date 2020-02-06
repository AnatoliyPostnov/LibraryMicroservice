package com.postnov.clientService.Controller;

import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunicateWithReceivedBookService {

    private final LibraryCardService libraryCardService;

    public CommunicateWithReceivedBookService(
            LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/CommunicateWithReceivedBook/filter")
    public LibraryCardDto getLibraryCardDtoById(
            @RequestParam("Id") Long Id) throws Exception {
        return libraryCardService.getLibraryCardDtoById(Id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/CommunicateWithReceivedBook/get/Id")
    public Long getLibraryCardIdByPassportNumberAndSeries(
            @RequestParam("PassportNumber") String passportNumber,
            @RequestParam("PassportSeries") String passportSeries) throws Exception {
        return libraryCardService.getLibraryCardByPassportNumberAndSeries(passportNumber, passportSeries).getId();
    }

}
