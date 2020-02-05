package com.postnov.bookService.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SetBookDto {

    @NotNull
    @NotEmpty
    private List<BookDto> booksDto = new ArrayList<>();

    public List<BookDto> getBooksDto() {
        return booksDto;
    }

    public void setBooksDto(List<BookDto> booksDto) {
        this.booksDto = booksDto;
    }

}
