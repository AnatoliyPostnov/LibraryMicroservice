package com.postnov.bookService.Exception.notFoundException;

public class FindBookByIdWasNotFoundException extends Exception {

    public FindBookByIdWasNotFoundException(Long Id) {
        super("Book with id: " + Id + " was not found");
    }
}
