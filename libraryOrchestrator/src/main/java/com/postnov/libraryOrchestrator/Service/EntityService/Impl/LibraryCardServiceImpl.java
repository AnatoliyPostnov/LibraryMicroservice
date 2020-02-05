package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Entity.AddLibraryCardJson;
import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Repository.LibraryCardRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final LibraryCardRepository libraryCardRepository;

    private final RestTemplate restTemplate;

    @Value("${urlLibraryCardFilterByNumberAndSeries}")
    private String urlLibraryCardFilterByNumberAndSeries;

    @Value("${urlLibraryCardFilterByFromLibraryCardsIdAndToLibraryCardsId}")
    private String urlLibraryCardFilterByFromLibraryCardsIdAndToLibraryCardsId;

    public LibraryCardServiceImpl(
            LibraryCardRepository libraryCardRepository,
            RestTemplate restTemplate) {
        this.libraryCardRepository = libraryCardRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    @Override
    public void saveJsonInDB(String addLibraryCardJson) {
        libraryCardRepository.save(new AddLibraryCardJson(addLibraryCardJson));
    }

    @Transactional
    @Override
    public void deleteJson(JsonMessage addLibraryCardJson) {
        libraryCardRepository.delete((AddLibraryCardJson) addLibraryCardJson);
    }

    @Transactional
    @Override
    public List<JsonMessage> getJson() {
        List<AddLibraryCardJson> addLibraryCardsJson = libraryCardRepository.findAll();
        return addLibraryCardsJson.stream().map(x -> (JsonMessage) x).collect(Collectors.toList());
    }

    @Override
    public String getLibraryCardDtoByPassportNumberAndSeries(String number, String series) {
        String uri = String.format(urlLibraryCardFilterByNumberAndSeries, number, series);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        String uri = String.format(urlLibraryCardFilterByFromLibraryCardsIdAndToLibraryCardsId,
                fromLibraryCardsId, toLibraryCardId);
        return restTemplate.getForObject(uri, String.class);
    }
}
