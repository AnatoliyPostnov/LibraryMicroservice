package com.postnov.bookService.Service.EntityService;

import com.postnov.bookService.Dto.BookDto;
import com.postnov.bookService.Entity.Book;
import com.postnov.bookService.Exception.notFoundException.FindBookByIdWasNotFoundException;

import java.util.List;

public interface BookService {

    void saveBooks(List<BookDto> booksDto);

    void deleteBookByBook(Book book);

    void receivedBook(BookDto bookDto);

    void returnBook(Long bookId);

    BookDto getBookDtoByBookNameAndBookVolume(String name, Integer volume);

    BookDto getReceivedBookDtoByBookNameAndVolume(String name, Integer volume);

    BookDto getReceivedBookDtoById(Long Id);

    BookDto getBookDtoById(Long Id) throws FindBookByIdWasNotFoundException;

    BookDto makeBookDto(Book book);

    Book getBookByBookNameAndVolume(String name, Integer volume);

    Book getReceivedBookByBookNameAndVolume(String name, Integer volume);

    Book getBookById(Long Id) throws FindBookByIdWasNotFoundException;

    Book getReceivedBookById(Long Id);

    Long getBookIdByBookDto(BookDto bookDto);

    Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume);

    List<BookDto> getBooksDtoByAuthorNameAndAuthorSurname(String name, String surname) throws FindBookByIdWasNotFoundException;

    List<BookDto> getBooksDto(Long fromBookId, Long toBookId);

    List<Long> getReceivedBooksIdByBookName(String booksName);

}