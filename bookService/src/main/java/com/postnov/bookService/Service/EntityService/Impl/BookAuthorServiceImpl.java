package com.postnov.bookService.Service.EntityService.Impl;

import com.postnov.bookService.Entity.BookAuthor;
import com.postnov.bookService.Exception.notFoundException.FindBooksIdByAuthorIdWasNotFoundException;
import com.postnov.bookService.Repository.BookAuthorRepository;
import com.postnov.bookService.Service.EntityService.BookAuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Transactional
    @Override
    public void saveAuthorsIdAndBookId(List<Long> authorsId, Long bookId) {
        for (Long authorId : authorsId) {
            bookAuthorRepository.save(new BookAuthor(bookId, authorId));
        }
    }

    @Override
    public List<Long> getAuthorsIdByBookId(Long bookId) {
        return bookAuthorRepository.findAuthorsIdByBookId(bookId);
    }

    @Override
    public Long getBookIdByAuthorId(Long authorId) {
        return bookAuthorRepository.findBooksIdByAuthorId(authorId)
                .orElseThrow(() -> new FindBooksIdByAuthorIdWasNotFoundException(authorId));
    }

    @Transactional
    @Override
    public void deleteBookAuthorByAuthorId(Long author_id) {
        bookAuthorRepository.deleteBookAuthorByAuthorId(author_id);
    }
}