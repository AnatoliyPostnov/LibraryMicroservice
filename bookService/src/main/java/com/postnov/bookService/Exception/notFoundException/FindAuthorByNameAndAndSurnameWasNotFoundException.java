package com.postnov.bookService.Exception.notFoundException;

public class FindAuthorByNameAndAndSurnameWasNotFoundException extends RuntimeException {

    public FindAuthorByNameAndAndSurnameWasNotFoundException(String name, String surname) {
        super(
                "Author with name: " + name +
                        " surname: " + surname +
                        " was not found");
    }
}
