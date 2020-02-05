package com.postnov.bookService.Controller;

import com.postnov.bookService.Exception.notFoundException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@ControllerAdvice
@RequestMapping
public class ExceptionController {

    @ExceptionHandler({
            FindAuthorByIdWasNotFoundException.class,
            FindAuthorByNameAndAndSurnameWasNotFoundException.class,
            FindBookByIdWasNotFoundException.class,
            FindBooksIdByAuthorIdWasNotFoundException.class,
            FindReceivedBookByIdWasNotFoundException.class,
            FindReceivedBookByNameAndVolumeWasNotFoundException.class,
            FindReturnBookByNameAndVolumeWasNotFoundException.class
    })
    protected ResponseEntity<RuntimeException> applicationExceptionNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

}
