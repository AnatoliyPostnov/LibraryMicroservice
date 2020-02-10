package com.postnov.libraryOrchestrator.Service.EntityService;

import org.springframework.http.ResponseEntity;

public interface BookService extends EntityService {

    ResponseEntity<String> getBookDtoByBookNameAndBookVolume(String bookName, Integer bookVolume);

    ResponseEntity<String> getBooksDto(Long fromBookId, Long toBookId);

    ResponseEntity<String> getBooksDtoByAuthorNameAndSurname(String authorName, String authorSurname);

}
