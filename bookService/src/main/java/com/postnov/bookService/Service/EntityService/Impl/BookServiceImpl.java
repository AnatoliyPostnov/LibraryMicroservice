package com.postnov.bookService.Service.EntityService.Impl;

import com.postnov.bookService.Dto.AuthorDto;
import com.postnov.bookService.Dto.BookDto;
import com.postnov.bookService.Dto.ListReceivedBookIdDto;
import com.postnov.bookService.Entity.Author;
import com.postnov.bookService.Entity.Book;
import com.postnov.bookService.Exception.notFoundException.*;
import com.postnov.bookService.Repository.BookRepository;
import com.postnov.bookService.Service.EntityService.AuthorService;
import com.postnov.bookService.Service.EntityService.BookAuthorService;
import com.postnov.bookService.Service.EntityService.BookService;
import com.postnov.bookService.Service.OtherService.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final ConvertService<BookDto, Book> convertServiceBook;

    private final ConvertService<AuthorDto, Author> convertServiceAuthor;

    private final BookAuthorService bookAuthorService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           ConvertService<BookDto, Book> convertServiceBook,
                           ConvertService<AuthorDto, Author> convertServiceAuthor,
                           BookAuthorService bookAuthorService) {

        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.convertServiceBook = convertServiceBook;
        this.convertServiceAuthor = convertServiceAuthor;
        this.bookAuthorService = bookAuthorService;
    }

    @Transactional
    @Override
    public void saveBooks(List<BookDto> booksDto) {

        for (BookDto bookDto : booksDto) {
            if (!bookRepository.findBookByBookNameAndVolume(
                    bookDto.getName(),
                    bookDto.getVolume())
                    .isPresent()) {
                Book book = convertServiceBook.convertFromDto(bookDto, Book.class);
                authorService.saveAuthors(
                        bookDto.getAuthors(),
                        bookRepository.save(book).getId());
            }
        }
    }

    @Transactional
    @Override
    public void deleteBookByBook(Book book) {
        authorService.deleteAuthorByBook(book);
        bookRepository.deleteBookByNameAndVolume(book.getName(), book.getVolume());
    }

    @Transactional
    @Override
    public void deleteBookByBookId(Long bookId) {
        authorService.deleteAuthorByBookId(bookId);
        bookRepository.deleteBookById(bookId);
    }

    @Transactional
    @Override
    public void receivedBook(BookDto bookDto) {
        Long Id = getBookByBookNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
        bookRepository.receivedBookById(Id);
    }

    @Transactional
    @Override
    public void returnBook(Long bookId) {
        bookRepository.returnBook(bookId);
    }

    @Override
    public BookDto getBookDtoByBookNameAndBookVolume(String name, Integer volume) {
        return makeBookDto(getBookByBookNameAndVolume(name, volume));
    }

    @Override
    public BookDto getReceivedBookDtoByBookNameAndVolume(String name, Integer volume) {
        return makeBookDto(getReceivedBookByBookNameAndVolume(name, volume));
    }

    @Override
    public BookDto getReceivedBookDtoById(Long Id) {
        return makeBookDto(getReceivedBookById(Id));
    }

    @Override
    public BookDto getBookDtoById(Long Id) throws FindBookByIdWasNotFoundException {
        return makeBookDto(getBookById(Id));
    }

    @Override
    public BookDto makeBookDto(Book book) {
        BookDto bookDto = convertServiceBook.convertToDto(book,
                BookDto.class);

        bookDto.setAuthors(
                convertServiceAuthor.convertToSetDto(
                        authorService.getAuthorsByBook(book),
                        AuthorDto.class)
        );
        return bookDto;
    }

    @Override
    public BookDto getBookDtoExistingOfTheLibraryById(Long Id) {
        return makeBookDto(bookRepository.findById(Id).orElseThrow(
                () -> new FindBookByIdExistingOfTheLibraryWasNotFoundException(Id)));
    }

    @Override
    public Book getReceivedBookById(Long Id) {
        return bookRepository.findReceivedBookById(Id).orElseThrow(
                () -> new FindReceivedBookByIdWasNotFoundException(Id));
    }

    @Override
    public Book getReceivedBookByBookNameAndVolume(String name, Integer volume) {
        return bookRepository.findReceivedBookByBookNameAndVolume(name, volume).orElseThrow(
                () -> new FindReceivedBookByNameAndVolumeWasNotFoundException(name, volume));
    }

    @Override
    public Book getBookByBookNameAndVolume(String name, Integer volume) {
        return bookRepository.findReturnBookByBookNameAndVolume(name, volume).orElseThrow(
                () -> new FindReturnBookByNameAndVolumeWasNotFoundException(name, volume));
    }

    @Override
    public Book getBookById(Long Id) throws FindBookByIdWasNotFoundException {
        return bookRepository.findBookById(Id).orElseThrow(
                () -> new FindBookByIdWasNotFoundException(Id));
    }

    @Override
    public Long getBookIdByBookDto(BookDto bookDto) {
        return getBookByBookNameAndVolume(bookDto.getName(), bookDto.getVolume()).getId();
    }

    @Override
    public Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume) {
        return getBookByBookNameAndVolume(bookName, bookVolume).getId();
    }

    @Override
    public ListReceivedBookIdDto getReceivedBooksIdByBookName(String bookName) {
        return new ListReceivedBookIdDto(bookRepository.findReceivedBooksIdByBooksName(bookName));
    }

    @Override
    public List<BookDto> getBooksDtoByAuthorNameAndAuthorSurname(String name, String surname)
            throws FindBookByIdWasNotFoundException {

        List<Author> authors = authorService.getAuthorsByNameAndSurname(name, surname);
        Iterator<Author> authorIterator = authors.iterator();
        List<BookDto> booksDto = new ArrayList<>();

        while (authorIterator.hasNext()) {
            Long author_id = authorIterator.next().getId();
            Long book_id = bookAuthorService.getBookIdByAuthorId(author_id);
            booksDto.add(getBookDtoById(book_id));
        }
        return booksDto;
    }

    @Override
    public List<BookDto> getBooksDto(Long fromBookId, Long toBookId) {
        List<BookDto> booksDto = new ArrayList<>();
        for (Long i = fromBookId; i <= toBookId; ++i) {
            try {
                booksDto.add(getBookDtoById(i));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return booksDto;
    }
}
