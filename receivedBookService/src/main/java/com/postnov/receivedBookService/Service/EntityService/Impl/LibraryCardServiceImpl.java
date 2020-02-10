package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.Client.LibraryCardServiceClient;
import com.postnov.receivedBookService.Dto.LibraryCardDto;
import com.postnov.receivedBookService.Service.EntityService.LibraryCardService;
import org.springframework.stereotype.Service;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final LibraryCardServiceClient libraryCardServiceClient;

    public LibraryCardServiceImpl(LibraryCardServiceClient libraryCardServiceClient) {
        this.libraryCardServiceClient = libraryCardServiceClient;
    }

    @Override
    public LibraryCardDto getLibraryCardDtoById(Long Id) {
        return libraryCardServiceClient.getLibraryCardDtoById(Id);
    }

    @Override
    public Long getLibraryCardIdByPassportNumberAndSeries(String number, String series) {
        return libraryCardServiceClient.getLibraryCardIdByPassportNumberAndSeries(number, series);
    }

    @Override
    public void deleteLibraryCard(String number, String series) {
        libraryCardServiceClient.deleteLibraryCard(number, series);
    }
}
