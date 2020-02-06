package com.postnov.receivedBookService.Service.ConsumerSevice;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConsumeService {
    public void getMessage() throws IOException, TimeoutException;
}
