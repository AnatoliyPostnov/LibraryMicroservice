package com.postnov.clientService.Exception.notFoundException;

public class FindClientByPassportIdWasNotFoundException extends RuntimeException {

    public FindClientByPassportIdWasNotFoundException(Long passportId) {
        super("Client with passport_id: " + passportId +
                " was not found");
    }
}
