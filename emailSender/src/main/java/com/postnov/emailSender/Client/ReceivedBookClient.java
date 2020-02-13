package com.postnov.emailSender.Client;

import com.postnov.emailSender.Config.ClientConfig;
import com.postnov.emailSender.Dto.ReceivedBookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ReceivedBookService", url = "http://localhost:8083", configuration = ClientConfig.class)
public interface ReceivedBookClient {

    @RequestMapping(method = RequestMethod.GET, value = "/all/received/books/for/email/sender")
    List<ReceivedBookDto> getAllReceivedBook();

    @RequestMapping(method = RequestMethod.GET, value = "/received/books/filter")
    List<ReceivedBookDto> getReceivedBooksByPassportNumberAndSeries(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("passportSeries") String passportSeries);

}
