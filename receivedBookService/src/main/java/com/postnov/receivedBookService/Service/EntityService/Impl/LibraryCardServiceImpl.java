package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.Dto.LibraryCardDto;
import com.postnov.receivedBookService.Service.EntityService.LibraryCardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final RestTemplate restTemplate;

    @Value("${urlGetLibraryCardDtoFilterId}")
    private String urlGetLibraryCardDtoFilterId;

    @Value("${urlGetLibraryCardIdFilterNumberAndSeries}")
    private String urlGetLibraryCardIdFilterNumberAndSeries;

    public LibraryCardServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public LibraryCardDto getLibraryCardDtoById(Long Id) {
        String uri = String.format(urlGetLibraryCardDtoFilterId, Id);
        return restTemplate.getForObject(uri, LibraryCardDto.class);
    }

    @Override
    public Long getLibraryCardIdByPassportNumberAndSeries(String number, String series) {
        String uri = String.format(urlGetLibraryCardIdFilterNumberAndSeries, number, series);
        return restTemplate.getForObject(uri, Long.class);
    }

}
