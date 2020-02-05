package com.postnov.clientService.Service.ConsumerService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ConsumerAddClientJsonService {

    public void getJsonMessage() throws IOException, TimeoutException;
}
