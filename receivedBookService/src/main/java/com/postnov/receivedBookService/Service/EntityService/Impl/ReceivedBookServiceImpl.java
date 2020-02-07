package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.Dto.BookDto;
import com.postnov.receivedBookService.Dto.LibraryCardDto;
import com.postnov.receivedBookService.Dto.ReceivedBookDto;
import com.postnov.receivedBookService.Entity.ReceivedBook;
import com.postnov.receivedBookService.Entity.ReceivedBookMessage;
import com.postnov.receivedBookService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.receivedBookService.Repository.ReceivedBookMessageRepository;
import com.postnov.receivedBookService.Repository.ReceivedBookRepository;
import com.postnov.receivedBookService.Service.EntityService.BookService;
import com.postnov.receivedBookService.Service.EntityService.LibraryCardService;
import com.postnov.receivedBookService.Service.EntityService.ReceivedBookService;
import com.postnov.receivedBookService.Service.OtherService.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceivedBookServiceImpl implements ReceivedBookService {

    private final Logger logger = LoggerFactory.getLogger(ReceivedBookServiceImpl.class);

    private final ReceivedBookRepository receivedBookRepository;

    private final ReceivedBookMessageRepository receivedBookMessageRepository;

    private final ConvertService<ReceivedBookDto, ReceivedBook> convertServiceReceivedBook;

    private final BookService bookService;

    private final LibraryCardService libraryCardService;

    public ReceivedBookServiceImpl(
            ReceivedBookRepository receivedBookRepository,
            ReceivedBookMessageRepository receivedBookMessageRepository,
            ConvertService<ReceivedBookDto, ReceivedBook> convertServiceReceivedBook,
            BookService bookService,
            LibraryCardService libraryCardService) {
        this.receivedBookRepository = receivedBookRepository;
        this.receivedBookMessageRepository = receivedBookMessageRepository;
        this.convertServiceReceivedBook = convertServiceReceivedBook;
        this.bookService = bookService;
        this.libraryCardService = libraryCardService;
    }


    @Override
    public void saveReceivedBookMessage(String message) {
        receivedBookMessageRepository.save(new ReceivedBookMessage(message));
    }

    @Override
    public void deleteLibraryCard(String number, String series) {
        if (getReceivedBooksByPassportNumberAndSeries(number, series).size() == 0) {
            libraryCardService.deleteLibraryCard(number, series);
        } else {
            logger.error(" [*] Client with passport number: " + number +
                    " series: " + series + " owes books!!!");
        }
    }

    @Override
    public void deleteBookByBookNameAndVolume(String name, Integer volume) {
        Long bookId = bookService.getBookIdByBookNameAndBookVolume(name, volume);
        receivedBookRepository.deleteByBookId(bookId);
        bookService.deleteBookByBookId(bookId);
    }

    @Override
    public List<ReceivedBookDto> getReceivedBooksByPassportNumberAndSeries(
            String passportNumber, String passportSeries) {
        return new ArrayList<>(getReceivedBooks(passportNumber, passportSeries, false));
    }

    @Override
    public List<ReceivedBookDto> getHistoryReceivedBooksByPassportNumberAndSeries(
            String passportNumber, String passportSeries) {
        return getReceivedBooks(passportNumber, passportSeries, true);
    }

    @Override
    public List<ReceivedBookDto> getAllReceivedBook(
            Long fromReceivedBookId, Long toReceivedBookId, Boolean forSendEmailClient) {
        List<ReceivedBook> receivedBooks;
        if (forSendEmailClient) {
            receivedBooks = receivedBookRepository.findAllReceivedBookForScheduled();
        } else {
            receivedBooks = receivedBookRepository.findAllReceivedBook(fromReceivedBookId, toReceivedBookId);
        }
        return convertReceivedBooksToReceivedBooksDto(receivedBooks);
    }

    @Transactional
    @Override
    public List<ReceivedBookDto> getReceivedBooks(String number, String series, Boolean historyOrNot) {
        Long libraryCardId = libraryCardService.getLibraryCardIdByPassportNumberAndSeries(number, series);
        List<ReceivedBook> receivedBooks;
        if (historyOrNot) {
            receivedBooks = receivedBookRepository.findHistoryReceivedBookByLibraryCardId(libraryCardId);
        } else {
            receivedBooks = receivedBookRepository.findReceivedBookByLibraryCardId(libraryCardId);
        }
        return convertReceivedBooksToReceivedBooksDto(receivedBooks);
    }

    @Override
    public List<ReceivedBookDto> convertReceivedBooksToReceivedBooksDto(List<ReceivedBook> receivedBooks) {
        return receivedBooks.stream()
                .map(receivedBook -> {
                    ReceivedBookDto receivedBookDto = convertServiceReceivedBook.convertToDto(receivedBook, ReceivedBookDto.class);
                    receivedBookDto.setBook(bookService.getReceivedBookDtoById(receivedBook.getBookId()));
                    receivedBookDto.setLibraryCard(libraryCardService.getLibraryCardDtoById(receivedBook.getLibraryCardId()));
                    return receivedBookDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void receivedBook(ReceivedBookDto receivedBookDto) {
        LibraryCardDto libraryCardDto = receivedBookDto.getLibraryCard();
        BookDto bookDto = receivedBookDto.getBook();
        Long libraryCardId = libraryCardService.getLibraryCardIdByPassportNumberAndSeries(
                libraryCardDto.getClient().getPassport().getNumber(),
                libraryCardDto.getClient().getPassport().getSeries());
        Long bookId = bookService.getBookIdByBookNameAndBookVolume(
                bookDto.getName(), bookDto.getVolume());
        bookService.receivedBook(bookDto);
        ReceivedBook receivedBook = convertServiceReceivedBook
                .convertFromDto(receivedBookDto, ReceivedBook.class)
                .setBookId(bookId)
                .setLibraryCardId(libraryCardId)
                .setDateOfBookReceiving(LocalDate.now());
        receivedBookRepository.save(receivedBook);
    }

    @Transactional
    @Override
    public void returnBooks(String number, String series, String bookName) {
        Long libraryCardId = libraryCardService.getLibraryCardIdByPassportNumberAndSeries(number, series);
        List<Long> booksId = bookService.getReceivedBooksIdByBookName(bookName).getReceivedBookId();
        for (Long bookId : booksId) {
            Optional<ReceivedBook> receivedBook = receivedBookRepository
                    .findReceivedBook(libraryCardId, bookId);
            receivedBook.ifPresent(book -> receivedBookRepository.returnBook(
                    LocalDate.now(),
                    book.getDateOfBookReceiving(),
                    book.getBookId(),
                    book.getLibraryCardId()));
            bookService.returnBook(bookId);
        }
    }
}
