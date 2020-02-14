package com.postnov.clientService.Controller;

import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CommunicateWithReceivedBook")
public class CommunicateWithReceivedBookService {

    private final LibraryCardService libraryCardService;

    public CommunicateWithReceivedBookService(
            LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping("/filter")
    public LibraryCardDto getLibraryCardDtoById(
            @RequestParam("Id") Long Id) throws Exception {
        return libraryCardService.getLibraryCardDtoById(Id);
    }

    @GetMapping("/get/Id")
    public Long getLibraryCardIdByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) throws Exception {
        return libraryCardService.getLibraryCardByPassportNumberAndSeries(passportNumber, passportSeries).getId();
    }

    @DeleteMapping("/delete/filter")
    public void deleteLibraryCard(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries) throws Exception {
        libraryCardService.deleteLibraryCard(passportNumber, passportSeries);
    }
}
