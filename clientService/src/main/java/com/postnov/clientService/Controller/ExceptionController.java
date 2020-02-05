package com.postnov.clientService.Controller;

import com.postnov.clientService.Exception.notFoundException.*;
import com.postnov.clientService.Exception.other.LibraryCardImpossibleDeleteException;
import com.postnov.clientService.Exception.other.LibraryCardImpossibleSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@ControllerAdvice
@RequestMapping
public class ExceptionController {

    @ExceptionHandler({
            FindClientByIdWasNotFoundException.class,
            FindClientByPassportIdWasNotFoundException.class,
            FindLibraryCardByClientIdWasNotFoundException.class,
            FindLibraryCardByIdWasNotFoundException.class,
            FindPassportByIdWasNotFoundException.class,
            FindPassportByPassportNumberAndSeriesWasNotFoundException.class
    })
    protected ResponseEntity<RuntimeException> applicationExceptionNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            LibraryCardImpossibleDeleteException.class,
            LibraryCardImpossibleSaveException.class
    })
    protected ResponseEntity<RuntimeException> applicationExceptionForbidden(RuntimeException ex) {
        return new ResponseEntity<>(ex, HttpStatus.FORBIDDEN);
    }
}
