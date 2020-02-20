package com.postnov.clientService.Service.ConsumerService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConsumerAddClientJsonService {

    void getJsonMessage(String message) throws IOException, TimeoutException;
}
