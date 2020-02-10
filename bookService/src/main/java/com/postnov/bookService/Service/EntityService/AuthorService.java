package com.postnov.bookService.Service.EntityService;

import com.postnov.bookService.Dto.AuthorDto;
import com.postnov.bookService.Entity.Author;
import com.postnov.bookService.Entity.Book;

import java.util.List;

public interface AuthorService {

    void saveAuthors(List<AuthorDto> authorsDto, Long book_id);

    void deleteAuthorByBook(Book book);

    void deleteAuthorByBookId(Long bookId);

    List<Author> getAuthorsByBook(Book book);

    List<Author> getAuthorsByBookId(Long bookId);

    List<Author> getAuthorsByNameAndSurname(String name, String surname);
}
