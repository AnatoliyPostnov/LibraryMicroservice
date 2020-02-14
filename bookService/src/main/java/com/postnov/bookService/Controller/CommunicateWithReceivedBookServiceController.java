package com.postnov.bookService.Controller;

import com.postnov.bookService.Dto.BookDto;
import com.postnov.bookService.Dto.ListReceivedBookIdDto;
import com.postnov.bookService.Service.EntityService.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CommunicateWithReceivedBook")
public class CommunicateWithReceivedBookServiceController {

    private final BookService bookService;

    public CommunicateWithReceivedBookServiceController(
            BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/filter")
    public BookDto getReceivedBookDtoById(
            @RequestParam("ReceivedBookId") Long Id) {
        return bookService.getReceivedBookDtoById(Id);
    }

    @GetMapping(value = "/BookDtoExistingOfTheLibrary/filter")
    public BookDto getBookDtoExistingOfTheLibraryById(
            @RequestParam("BookId") Long Id) {
        return bookService.getBookDtoExistingOfTheLibraryById(Id);
    }

    @GetMapping(value = "/BookId/filter")
    public Long getBookIdByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume) {
        return bookService.getBookIdByBookNameAndBookVolume(bookName, bookVolume);
    }

    @GetMapping(value = "/receivedBookId/filter")
    public ListReceivedBookIdDto getReceivedBooksIdByBookName(
            @RequestParam("bookName") String bookName) {
        return bookService.getReceivedBooksIdByBookName(bookName);
    }

    @PostMapping("/received/book")
    public void postReceivedBook(@RequestBody BookDto bookDto) {
        bookService.receivedBook(bookDto);
    }

    @PostMapping("/return/book")
    public void postReturnBook(@RequestBody Long bookId) {
        bookService.returnBook(bookId);
    }

    @DeleteMapping("/delete/filter")
    public void deleteBookByBookId(@RequestParam("bookId") Long bookId) {
        bookService.deleteBookByBookId(bookId);
    }

}