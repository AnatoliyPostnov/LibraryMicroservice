package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Client.LibraryCardServiceClient;
import com.postnov.libraryOrchestrator.Entity.AddLibraryCardJson;
import com.postnov.libraryOrchestrator.Entity.Message;
import com.postnov.libraryOrchestrator.Repository.LibraryCardRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.LibraryCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final LibraryCardRepository libraryCardRepository;

    private final LibraryCardServiceClient libraryCardServiceClient;

    public LibraryCardServiceImpl(
            LibraryCardRepository libraryCardRepository,
            LibraryCardServiceClient libraryCardServiceClient) {
        this.libraryCardRepository = libraryCardRepository;
        this.libraryCardServiceClient = libraryCardServiceClient;
    }

    @Transactional
    @Override
    public void saveJsonInDB(String addLibraryCardJson) {
        libraryCardRepository.save(new AddLibraryCardJson(addLibraryCardJson));
    }

    @Transactional
    @Override
    public void deleteJson(Message addLibraryCardJson) {
        libraryCardRepository.delete((AddLibraryCardJson) addLibraryCardJson);
    }

    @Override
    public List<Message> getJson() {
        List<AddLibraryCardJson> addLibraryCardsJson = libraryCardRepository.findAll();
        return addLibraryCardsJson.stream().map(x -> (Message) x).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> getLibraryCardDtoByPassportNumberAndSeries(String number, String series) {
        return libraryCardServiceClient.getLibraryCardDtoByPassportNumberAndSeries(number, series);
    }

    @Override
    public ResponseEntity<String> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        return libraryCardServiceClient.getLibraryCards(fromLibraryCardsId, toLibraryCardId);
    }
}
