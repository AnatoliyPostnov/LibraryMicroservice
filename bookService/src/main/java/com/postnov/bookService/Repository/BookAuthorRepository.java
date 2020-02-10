package com.postnov.bookService.Repository;

import com.postnov.bookService.Entity.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {

    @Modifying
    @Query(value = "delete from BookAuthor WHERE author_id = :author_id")
    void deleteBookAuthorByAuthorId(
            @Param("author_id") Long author_id);

    @Query("SELECT ba.book_id FROM BookAuthor ba WHERE ba.author_id = :author_id")
    Optional<Long> findBooksIdByAuthorId(
            @Param("author_id") Long author_id);

    @Query("SELECT ba.author_id FROM BookAuthor ba WHERE ba.book_id = :book_id")
    List<Long> findAuthorsIdByBookId(
            @Param("book_id") Long book_id);

}