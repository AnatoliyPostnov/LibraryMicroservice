package com.postnov.bookService;

import com.postnov.bookService.Dto.BookDto;

import java.util.ArrayList;
import java.util.List;

public class ListBookDto {

    public List<BookDto> getBooksDto() {
        return booksDto;
    }

    public void setBooksDto(List<BookDto> booksDto) {
        this.booksDto = booksDto;
    }

    private List<BookDto> booksDto = new ArrayList<>();
}
