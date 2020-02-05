package com.postnov.bookService.Exception.notFoundException;

public class FindReceivedBookByIdWasNotFoundException extends RuntimeException {

    public FindReceivedBookByIdWasNotFoundException(Long Id) {
        super("Received book with id: " + Id +
                " was not found exception");
    }
}
