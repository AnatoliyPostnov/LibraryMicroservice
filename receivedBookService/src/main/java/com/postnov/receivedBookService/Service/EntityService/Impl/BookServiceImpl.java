package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.Client.BookServiceClient;
import com.postnov.receivedBookService.Dto.BookDto;
import com.postnov.receivedBookService.Dto.ListReceivedBookIdDto;
import com.postnov.receivedBookService.Service.EntityService.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookServiceClient bookServiceClient;

    public BookServiceImpl(BookServiceClient bookServiceClient) {
        this.bookServiceClient = bookServiceClient;
    }

    @Override
    public void receivedBook(BookDto bookDto) {
        bookServiceClient.receivedBook(bookDto);
    }

    @Override
    public void returnBook(Long bookId) {
        bookServiceClient.returnBook(bookId);
    }

    @Override
    public void deleteBookByBookId(Long bookId) {
        bookServiceClient.deleteBook(bookId);
    }

    @Override
    public BookDto getReceivedBookDtoById(Long Id) {
        return bookServiceClient.getReceivedBookDtoById(Id);
    }

    @Override
    public BookDto getBookDtoById(Long Id) {
        return bookServiceClient.getBookDtoById(Id);
    }

    @Override
    public Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume) {
        return bookServiceClient.getBookIdByBookNameAndBookVolume(bookName, bookVolume);
    }

    @Override
    public ListReceivedBookIdDto getReceivedBooksIdByBookName(String bookName) {
        return bookServiceClient.getReceivedBooksIdByBookName(bookName);
    }

}
