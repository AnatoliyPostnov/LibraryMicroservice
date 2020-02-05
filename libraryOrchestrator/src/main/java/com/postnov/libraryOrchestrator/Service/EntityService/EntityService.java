package com.postnov.libraryOrchestrator.Service.EntityService;

import com.postnov.libraryOrchestrator.Entity.JsonMessage;

import java.util.List;

public interface EntityService {

    void saveJsonInDB(String Json);

    List<JsonMessage> getJson();

    void deleteJson(JsonMessage json);

}
