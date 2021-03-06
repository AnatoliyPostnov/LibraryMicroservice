package com.postnov.bookService.Service.EntityService.Impl;


import com.postnov.bookService.Dto.AuthorDto;
import com.postnov.bookService.Entity.Author;
import com.postnov.bookService.Entity.Book;
import com.postnov.bookService.Exception.notFoundException.FindAuthorByIdWasNotFoundException;
import com.postnov.bookService.Exception.notFoundException.FindAuthorByNameAndAndSurnameWasNotFoundException;
import com.postnov.bookService.Repository.AuthorRepository;
import com.postnov.bookService.Service.EntityService.AuthorService;
import com.postnov.bookService.Service.EntityService.BookAuthorService;
import com.postnov.bookService.Service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final ConvertService<AuthorDto, Author> convertService;

    private final BookAuthorService bookAuthorService;

    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            ConvertService<AuthorDto, Author> convertService,
            BookAuthorService bookAuthorService) {
        this.authorRepository = authorRepository;
        this.convertService = convertService;
        this.bookAuthorService = bookAuthorService;
    }

    @Transactional
    @Override
    public void saveAuthors(List<AuthorDto> authors, Long book_id) {
        List<Long> authors_id = new ArrayList<>();
        for (AuthorDto authorDto : authors) {
            Author author = convertService.convertFromDto(authorDto, Author.class);
            authors_id.add(authorRepository.save(author).getId());
        }
        bookAuthorService.saveAuthorsIdAndBookId(authors_id, book_id);
    }

    @Override
    public void deleteAuthorByBook(Book book) {
        deleteAuthorByBookId(book.getId());
    }

    @Transactional
    @Override
    public void deleteAuthorByBookId(Long bookId) {
        for (Author author : getAuthorsByBookId(bookId)) {
            bookAuthorService.deleteBookAuthorByAuthorId(author.getId());
            authorRepository.deleteAuthorById(author.getId());
        }
    }

    @Override
    public List<Author> getAuthorsByBook(Book book) {
        return getAuthorsByBookId(book.getId());
    }

    @Override
    public List<Author> getAuthorsByBookId(Long bookId) {
        List<Long> authorsId = bookAuthorService.getAuthorsIdByBookId(bookId);
        List<Author> authors = new ArrayList<>();
        for (Long authorId : authorsId) {
            authors.add(authorRepository.findAuthorById(authorId)
                    .orElseThrow(() -> new FindAuthorByIdWasNotFoundException(authorId)));
        }
        return authors;
    }

    @Override
    public List<Author> getAuthorsByNameAndSurname(String name, String surname) {
        List<Author> authors = authorRepository.findAuthorByNameAndSurname(name, surname);
        if (authors.isEmpty()) {
            throw new FindAuthorByNameAndAndSurnameWasNotFoundException(name, surname);
        }
        return authors;
    }

}