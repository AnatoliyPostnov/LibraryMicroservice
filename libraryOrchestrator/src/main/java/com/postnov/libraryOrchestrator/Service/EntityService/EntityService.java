package com.postnov.libraryOrchestrator.Service.EntityService;

import com.postnov.libraryOrchestrator.Entity.Message;

import java.util.List;

public interface EntityService {

    void saveJsonInDB(String Json);

    List<Message> getJson();

    void deleteJson(Message json);

}
