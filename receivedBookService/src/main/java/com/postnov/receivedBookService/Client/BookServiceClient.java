package com.postnov.receivedBookService.Client;


import com.postnov.receivedBookService.Config.ClientConfig;
import com.postnov.receivedBookService.Dto.BookDto;
import com.postnov.receivedBookService.Dto.ListReceivedBookIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "BookServiceClient", url = "http://localhost:8081", configuration = ClientConfig.class)
public interface BookServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/CommunicateWithReceivedBook/received/book")
    void receivedBook(@RequestBody BookDto bookDto);

    @RequestMapping(method = RequestMethod.POST, value = "/CommunicateWithReceivedBook/return/book")
    void returnBook(@RequestBody Long bookId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/CommunicateWithReceivedBook/delete/filter")
    void deleteBook(@RequestParam("bookId") Long bookId);

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/filter")
    BookDto getReceivedBookDtoById(@RequestParam("ReceivedBookId") Long receivedBookId);

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/BookDtoExistingOfTheLibrary/filter")
    BookDto getBookDtoById(@RequestParam("BookId") Long bookId);

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/BookId/filter")
    Long getBookIdByBookNameAndBookVolume(
            @RequestParam("bookName") String bookName,
            @RequestParam("bookVolume") Integer bookVolume);

    @RequestMapping(method = RequestMethod.GET, value = "/CommunicateWithReceivedBook/receivedBookId/filter")
    ListReceivedBookIdDto getReceivedBooksIdByBookName(@RequestParam("bookName") String bookName);

}