package com.postnov.bookService.Service.EntityService;

import java.util.List;

public interface BookAuthorService {

    void saveAuthorsIdAndBookId(List<Long> authors_id, Long book_id);

    void deleteBookAuthorByAuthorId(Long author_id);

    Long getBookIdByAuthorId(Long author_id);

    List<Long> getAuthorsIdByBookId(Long book_id);

}
