package com.postnov.receivedBookService.Exception.notFoundException;

public class FindPassportByIdWasNotFoundException extends RuntimeException {

    public FindPassportByIdWasNotFoundException(Long Id) {
        super("Passport with id: " + Id + " was not found");
    }
}
