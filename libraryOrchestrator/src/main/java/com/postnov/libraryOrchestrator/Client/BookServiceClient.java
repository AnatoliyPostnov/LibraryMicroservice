package com.postnov.libraryOrchestrator.Client;

import com.postnov.libraryOrchestrator.Config.ClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BookServiceClient", url = "http://localhost:8081", configuration = ClientConfig.class)
public interface BookServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/book/filter")
    ResponseEntity<String> getBookDtoByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume);

    @RequestMapping(method = RequestMethod.GET, value = "/books/filter")
    ResponseEntity<String> getBooksDto(
            @RequestParam("fromBookId") Long fromBookId,
            @RequestParam("toBookId") Long toBookId);

    @RequestMapping(method = RequestMethod.GET, value = "/books/author/filter")
    ResponseEntity<String> getBooksDtoByAuthorNameAndSurname(
            @RequestParam("authorName") String authorName,
            @RequestParam("authorSurname") String authorSurname);
}