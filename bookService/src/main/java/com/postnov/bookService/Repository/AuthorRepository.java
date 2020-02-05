package com.postnov.bookService.Repository;

import com.postnov.bookService.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    void deleteAuthorById(
            @Param("Id") Long Id);

    Optional<Author> findAuthorById(
            @Param("author_id") Long author_id);

    List<Author> findAuthorByNameAndSurname(
            @Param("name") String name,
            @Param("surname") String surname);

}