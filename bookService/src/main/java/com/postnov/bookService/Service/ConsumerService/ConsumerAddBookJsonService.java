package com.postnov.bookService.Service.ConsumerService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConsumerAddBookJsonService {

    public void getJsonMessage() throws IOException, TimeoutException;
}
