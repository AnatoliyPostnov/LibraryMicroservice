package com.postnov.libraryOrchestrator.Service.ProduserService;

import com.postnov.libraryOrchestrator.Service.EntityService.EntityService;

public interface ProducerSender {

    void send(String json, String queueName, EntityService entityService);
}
