package com.postnov.bookService.Service.EntityService;

import java.util.List;
import java.util.Set;

public interface BookAuthorService {

    void saveAuthorsIdAndBookId(List<Long> authors_id, Long book_id);

    void deleteBook_AuthorByAuthorId(Long author_id);

    Long getBookIdByAuthorId(Long author_id);

    List<Long> getAuthorsIdByBookId(Long book_id);

}
