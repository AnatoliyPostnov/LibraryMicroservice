package com.postnov.libraryOrchestrator.Service.EntityService;

public interface BookService extends EntityService {

    String getBookDtoByBookNameAndBookVolume(String bookName, Integer bookVolume);

    String getBooksDto(Long fromBookId, Long toBookId);

    String getBooksDtoByAuthorNameAndSurname(String authorName, String authorSurname);

}
