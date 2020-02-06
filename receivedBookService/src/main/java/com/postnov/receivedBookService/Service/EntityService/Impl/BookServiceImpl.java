package com.postnov.receivedBookService.Service.EntityService.Impl;

import com.postnov.receivedBookService.ConsumerEntity.ListReceivedBookId;
import com.postnov.receivedBookService.Dto.BookDto;
import com.postnov.receivedBookService.Service.EntityService.BookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookServiceImpl implements BookService {

    private final RestTemplate restTemplate;

    @Value("${urlGetBookDtoFilterId}")
    private String urlGetBookDtoFilterId;

    @Value("${urlPostReceivedBook}")
    private String urlPostReceivedBook;

    @Value("${urlGetBookIdByBookDto}")
    private String urlGetBookIdByBookDto;

    @Value("${urlPostReturnBook}")
    private String urlPostReturnBook;

    @Value("${urlGetReceivedBooksIdByBookName}")
    private String urlGetReceivedBooksIdByBookName;

    public BookServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void receivedBook(BookDto bookDto) {
        restTemplate.postForObject(urlPostReceivedBook, bookDto, BookDto.class);
    }

    @Override
    public void returnBook(Long bookId) {
        restTemplate.postForObject(urlPostReturnBook, bookId, Long.class);
    }

    @Override
    public BookDto getReceivedBookDtoById(Long Id) {
        String uri = String.format(urlGetBookDtoFilterId, Id);
        return restTemplate.getForObject(uri, BookDto.class);
    }

    @Override
    public Long getBookIdByBookNameAndBookVolume(String bookName, Integer bookVolume) {
        String uri = String.format(urlGetBookIdByBookDto, bookName, bookVolume);
        return restTemplate.getForObject(uri, Long.class);
    }

    @Override
    public ListReceivedBookId getReceivedBooksIdByBookName(String bookName) {
        String uri = String.format(urlGetReceivedBooksIdByBookName, bookName);
        return restTemplate.getForObject(uri, ListReceivedBookId.class);
    }

}
