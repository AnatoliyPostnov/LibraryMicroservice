package com.postnov.bookService.Controller;

import com.postnov.bookService.Dto.BookDto;
import com.postnov.bookService.Exception.notFoundException.FindBookByIdWasNotFoundException;
import com.postnov.bookService.Service.EntityService.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "book/filter")
    public BookDto getBookByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        return bookService.getBookDtoByBookNameAndBookVolume(bookName, bookVolume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/books/filter")
    public List<BookDto> getBooksFromBookIdToBookId(
            @RequestParam("fromBookId") Long fromBookId,
            @RequestParam("toBookId") Long toBookId) {
        return bookService.getBooksDto(fromBookId, toBookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "books/author/filter")
    public List<BookDto> getBooksByAuthorNameAndAuthorSurname(
            @RequestParam("authorName") String authorName,
            @RequestParam("authorSurname") String authorSurname)
            throws FindBookByIdWasNotFoundException {
        return bookService.getBooksDtoByAuthorNameAndAuthorSurname(authorName, authorSurname);
    }
}
