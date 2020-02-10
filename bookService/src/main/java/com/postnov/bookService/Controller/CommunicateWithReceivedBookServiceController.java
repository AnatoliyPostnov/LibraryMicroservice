package com.postnov.bookService.Controller;

import com.postnov.bookService.Dto.BookDto;
import com.postnov.bookService.Dto.ListReceivedBookIdDto;
import com.postnov.bookService.Service.EntityService.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunicateWithReceivedBookServiceController {

    private final BookService bookService;

    public CommunicateWithReceivedBookServiceController(
            BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/CommunicateWithReceivedBook/filter")
    public BookDto getReceivedBookDtoById(
            @RequestParam("ReceivedBookId") Long Id) {
        return bookService.getReceivedBookDtoById(Id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/CommunicateWithReceivedBook/BookDtoExistingOfTheLibrary/filter")
    public BookDto getBookDtoExistingOfTheLibraryById(
            @RequestParam("BookId") Long Id) {
        return bookService.getBookDtoExistingOfTheLibraryById(Id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/CommunicateWithReceivedBook/BookId/filter")
    public Long getBookIdByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        return bookService.getBookIdByBookNameAndBookVolume(bookName, bookVolume);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "CommunicateWithReceivedBook/receivedBookId/filter")
    public ListReceivedBookIdDto getReceivedBooksIdByBookName(
            @RequestParam("bookName") String bookName) {
        return bookService.getReceivedBooksIdByBookName(bookName);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/CommunicateWithReceivedBook/received/book")
    public void postReceivedBook(@RequestBody BookDto bookDto) {
        bookService.receivedBook(bookDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/CommunicateWithReceivedBook/return/book")
    public void postReturnBook(@RequestBody Long bookId) {
        bookService.returnBook(bookId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/CommunicateWithReceivedBook/delete/filter")
    public void deleteBookByBookId(@RequestParam("bookId") Long bookId) {
        bookService.deleteBookByBookId(bookId);
    }

}