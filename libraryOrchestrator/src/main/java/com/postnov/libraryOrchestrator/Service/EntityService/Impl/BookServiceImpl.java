package com.postnov.libraryOrchestrator.Service.EntityService.Impl;

import com.postnov.libraryOrchestrator.Client.BookServiceClient;
import com.postnov.libraryOrchestrator.Entity.AddBookJson;
import com.postnov.libraryOrchestrator.Entity.Message;
import com.postnov.libraryOrchestrator.Repository.BookRepository;
import com.postnov.libraryOrchestrator.Service.EntityService.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookServiceClient bookServiceClient;

    public BookServiceImpl(
            BookRepository bookRepository,
            BookServiceClient bookServiceClient) {
        this.bookRepository = bookRepository;
        this.bookServiceClient = bookServiceClient;
    }

    @Transactional
    @Override
    public void saveJsonInDB(String addBookJson) {
        bookRepository.save(new AddBookJson(addBookJson));
    }

    @Override
    public List<Message> getJson() {
        List<AddBookJson> addBooksJson = bookRepository.findAll();
        return addBooksJson.stream().map(x -> (Message) x).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteJson(Message addBookJson) {
        bookRepository.delete((AddBookJson) addBookJson);
    }

    @Override
    public ResponseEntity<String> getBookDtoByBookNameAndBookVolume(String bookName, Integer bookVolume) {
        return bookServiceClient.getBookDtoByBookNameAndBookVolume(bookName, bookVolume);
    }

    @Override
    public ResponseEntity<String> getBooksDto(Long fromBookId, Long toBookId) {
        return bookServiceClient.getBooksDto(fromBookId, toBookId);
    }

    @Override
    public ResponseEntity<String> getBooksDtoByAuthorNameAndSurname(String authorName, String authorSurname) {
        return bookServiceClient.getBooksDtoByAuthorNameAndSurname(authorName, authorSurname);
    }
}
