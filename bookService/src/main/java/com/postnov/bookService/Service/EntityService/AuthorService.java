package com.postnov.bookService.Service.EntityService;

import com.postnov.bookService.Dto.AuthorDto;
import com.postnov.bookService.Entity.Author;
import com.postnov.bookService.Entity.Book;

import java.util.List;
import java.util.Set;

public interface AuthorService {

    void saveAuthors(List<AuthorDto> authorsDto, Long book_id);

    void deleteAuthorByBook(Book book);

    List<Author> getAuthorsByBook(Book book);

    List<Author> getAuthorsByNameAndSurname(String name, String surname);
}
