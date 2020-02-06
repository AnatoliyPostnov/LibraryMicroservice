package com.postnov.receivedBookService.Service.EntityService;

import com.postnov.receivedBookService.ConsumerEntity.ListReceivedBookId;
import com.postnov.receivedBookService.Dto.BookDto;

import java.util.List;

public interface BookService {

    void receivedBook(BookDto bookDto);

    void returnBook(Long bookId);

    BookDto getReceivedBookDtoById(Long Id);

    Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume);

    ListReceivedBookId getReceivedBooksIdByBookName(String bookName);
}
