package com.postnov.clientService.Exception.notFoundException;

public class FindClientByIdWasNotFoundException extends RuntimeException {

    public FindClientByIdWasNotFoundException(Long Id) {
        super("Client with Id: " + Id + " was not found");
    }
}
