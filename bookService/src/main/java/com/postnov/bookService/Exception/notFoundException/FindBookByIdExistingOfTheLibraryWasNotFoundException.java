package com.postnov.bookService.Exception.notFoundException;

public class FindBookByIdExistingOfTheLibraryWasNotFoundException extends RuntimeException {

    public FindBookByIdExistingOfTheLibraryWasNotFoundException(Long Id){
        super("Book with id: " + Id + " was not found");
    }
}
