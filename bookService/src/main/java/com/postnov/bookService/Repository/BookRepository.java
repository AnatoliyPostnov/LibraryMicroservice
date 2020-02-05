package com.postnov.bookService.Repository;


import com.postnov.bookService.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    void deleteBookByNameAndVolume(
            @Param("name") String name,
            @Param("volume") Integer volume);

    @Modifying
    @Query(value = "update Book set isReceivedBook = 'true' where id = :Id")
    void receivedBookById(
            @Param("Id") Long Id);

    @Modifying
    @Query(value = "update Book set isReceivedBook = 'false' where id = :bookId")
    void returnBook(
            @Param("bookId") Long bookId);

    @Query(value = "select b from Book b " +
            "WHERE b.name = :bookName and b.volume = :volume")
    Optional<Book> findBookByBookNameAndVolume(
            @Param("bookName") String bookName,
            @Param("volume") Integer volume);

    @Query(value = "select b from Book b " +
            "WHERE b.name = :bookName and b.volume = :volume and b.isReceivedBook = 'false'")
    Optional<Book> findReturnBookByBookNameAndVolume(
            @Param("bookName") String bookName,
            @Param("volume") Integer volume);

    @Query(value = "select b from Book b " +
            "WHERE b.name = :bookName and b.volume = :volume and b.isReceivedBook = 'true'")
    Optional<Book> findReceivedBookByBookNameAndVolume(
            @Param("bookName") String bookName,
            @Param("volume") Integer volume);

    @Query(value = "select b from Book b " +
            "WHERE b.id = :Id and b.isReceivedBook = 'true'")
    Optional<Book> findReceivedBookById(
            @Param("Id") Long Id);

    @Query(value = "select b from Book b " +
            "WHERE b.id = :Id and b.isReceivedBook = 'false'")
    Optional<Book> findBookById(
            @Param("Id") Long Id);

    @Query(value = "select id from Book where name = :booksName and isReceivedBook = 'true'")
    List<Long> findReceivedBooksIdByBooksName(
            @Param("booksName") String booksName);

}