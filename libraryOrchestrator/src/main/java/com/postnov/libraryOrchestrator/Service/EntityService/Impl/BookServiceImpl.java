package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Entity.AddBookJson;
import com.postnov.libraryOrchestrator.Entity.JsonMessage;
import com.postnov.libraryOrchestrator.Repository.BookRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.BookService;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final RestTemplate restTemplate;

    @Value("${urlBookFilterByBookNameAndBookVolume}")
    private String urlBookFilterByBookNameAndBookVolume;

    @Value("${urlBooksFilterByFromBookIdAndToBookId}")
    private String urlBooksFilterByFromBookIdAndToBookId;

    @Value("${urlBooksFilterByAuthorNameAndSurname}")
    private String urlBooksFilterByAuthorNameAndSurname;

    public BookServiceImpl(
            BookRepository bookRepository,
            RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    @Override
    public void saveJsonInDB(String addBookJson) {
        bookRepository.save(new AddBookJson(addBookJson));
    }

    @Transactional
    @Override
    public List<JsonMessage> getJson() {
        List<AddBookJson> addBooksJson = bookRepository.findAll();
        return addBooksJson.stream().map(x -> (JsonMessage) x).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteJson(JsonMessage addBookJson) {
        bookRepository.delete((AddBookJson) addBookJson);
    }

    @Override
    public String getBookDtoByBookNameAndBookVolume(String bookName, Integer bookVolume) {
        String uri = String.format(urlBookFilterByBookNameAndBookVolume, bookName, bookVolume);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getBooksDto(Long fromBookId, Long toBookId) {
        String uri = String.format(urlBooksFilterByFromBookIdAndToBookId, fromBookId, toBookId);
        return restTemplate.getForObject(uri, String.class);
    }

    @Override
    public String getBooksDtoByAuthorNameAndSurname(String authorName, String authorSurname) {
        String uri = String.format(urlBooksFilterByAuthorNameAndSurname, authorName, authorSurname);
        return restTemplate.getForObject(uri, String.class);
    }
}
