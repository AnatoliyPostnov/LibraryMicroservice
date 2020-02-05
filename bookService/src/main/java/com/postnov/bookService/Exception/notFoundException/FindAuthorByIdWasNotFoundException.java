package com.postnov.bookService.Exception.notFoundException;

public class FindAuthorByIdWasNotFoundException extends RuntimeException {

    public FindAuthorByIdWasNotFoundException(Long authorId) {
        super("Author with" + authorId + "was not found");
    }
}
