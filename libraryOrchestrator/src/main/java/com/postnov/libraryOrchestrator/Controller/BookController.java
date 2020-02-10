package com.postnov.libraryOrchestrator.Controller;


import com.postnov.libraryOrchestrator.Service.EntityService.BookService;
import com.postnov.libraryOrchestrator.Service.ProduserService.BookProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private final BookService bookService;

    private final BookProducerService bookProducerService;

    public BookController(
            BookService bookService,
            BookProducerService bookProducerService) {
        this.bookService = bookService;
        this.bookProducerService = bookProducerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "add/books")
    public void addBooks(@RequestBody String addBookJson) {
        bookProducerService.send(addBookJson);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "book/filter")
    public ResponseEntity<String> getBookByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        return bookService.getBookDtoByBookNameAndBookVolume(bookName, bookVolume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/books/filter")
    public ResponseEntity<String> getBooksFromBookIdToBookId(
            @RequestParam("fromBookId") Long fromBookId,
            @RequestParam("toBookId") Long toBookId) {
        return bookService.getBooksDto(fromBookId, toBookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "books/author/filter")
    public ResponseEntity<String> getBooksByAuthorNameAndSurname(
            @RequestParam("authorName") String authorName,
            @RequestParam("authorSurname") String authorSurname) {
        return bookService.getBooksDtoByAuthorNameAndSurname(authorName, authorSurname);
    }

}
