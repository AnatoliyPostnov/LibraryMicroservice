package com.postnov.bookService.Service.ConsumerService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConsumerAddBookJsonService {

    void getJsonMessage(String message) throws IOException, TimeoutException;
}
