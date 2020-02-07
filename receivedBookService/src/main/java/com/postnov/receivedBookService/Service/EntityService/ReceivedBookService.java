package com.postnov.receivedBookService.Service.EntityService;

import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Entity.ReceivedBook;
import com.postnov.receivedBookService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceivedBookService {

    List<ReceivedBookDto> getReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    List<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(String passportNumber, String passportSeries);

    List<ReceivedBookDto> convertReceivedBooksToReceivedBooksDto(List<ReceivedBook> receivedBooks);

    List<ReceivedBookDto> getAllReceivedBook(Long fromReceivedBookId, Long toReceivedBookId, Boolean historyOrNot);

    List<ReceivedBookDto> getReceivedBooks(String number, String series, Boolean historyOrNot);

    void receivedBook(ReceivedBookDto receivedBookDto);

    void returnBooks(String number, String series, String bookName);

    void saveReceivedBookMessage(String message);

    void deleteLibraryCard(String number, String series);

    void deleteBookByBookNameAndVolume(String name, Integer volume);

}
