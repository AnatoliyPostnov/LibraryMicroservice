package com.postnov.receivedBookService.Service.EntityService;

import com.postnov.receivedBookService.Dto.BookDto;
import com.postnov.receivedBookService.Dto.ListReceivedBookIdDto;

public interface BookService {

    void receivedBook(BookDto bookDto);

    void returnBook(Long bookId);

    void deleteBookByBookId(Long bookId);

    BookDto getReceivedBookDtoById(Long Id);

    BookDto getBookDtoById(Long Id);

    Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume);

    ListReceivedBookIdDto getReceivedBooksIdByBookName(String bookName);

}
