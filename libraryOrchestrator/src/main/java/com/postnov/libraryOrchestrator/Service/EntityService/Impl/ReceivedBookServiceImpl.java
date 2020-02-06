package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Entity.ReceivedBookMessage;
import com.postnov.libraryOrchestrator.Repository.ReceivedBookRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.ReceivedBookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivedBookServiceImpl implements ReceivedBookService {

    private final ReceivedBookRepository receivedBookRepository;

    private final RestTemplate restTemplate;

    @Value("${urlReceivedBookFilterPassportNumberAndSeries}")
    private String urlReceivedBookFilterPassportNumberAndSeries;

    @Value("${urlReceivedBookHistoryFilterPassportNumberAndSeries}")
    private String urlReceivedBookHistoryFilterPassportNumberAndSeries;

    @Value("${urlReceivedBookFilterFromReceivedBookIdAndToReceivedBookId}")
    private String urlReceivedBookFilterFromReceivedBookIdAndToReceivedBookId;

    public ReceivedBookServiceImpl(
            ReceivedBookRepository receivedBookRepository,
            RestTemplate restTemplate) {
        this.receivedBookRepository = receivedBookRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        String uri = String.format(urlReceivedBookFilterPassportNumberAndSeries,
                passportNumber, passportSeries);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries) {
        String uri = String.format(urlReceivedBookHistoryFilterPassportNumberAndSeries,
                passportNumber, passportSeries);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId) {
        String uri = String.format(urlReceivedBookFilterFromReceivedBookIdAndToReceivedBookId,
                fromReceivedBookId, toReceivedBookId);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public void saveJsonInDB(String json) {
        receivedBookRepository.save(new ReceivedBookMessage(json));
    }

    @Override
    public List<JsonMessage> getJson() {
        List<ReceivedBookMessage> receivedBookMessages = receivedBookRepository.findAll();
        return receivedBookMessages.stream().map(x -> (JsonMessage) x).collect(Collectors.toList());
    }

    @Override
    public void deleteJson(JsonMessage json) {
        receivedBookRepository.delete((ReceivedBookMessage) json);
    }
}
