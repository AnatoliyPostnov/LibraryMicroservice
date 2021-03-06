package com.postnov.receivedBookService.Repository;

import com.postnov.receivedBookService.Entity.ReceivedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceivedBookRepository extends JpaRepository<ReceivedBook, Long> {

    @Modifying
    @Query(value = "update ReceivedBook rb set dateOfBookReturn = :dateOfBookReturn " +
            "where rb.bookId = :bookId and " +
            "rb.libraryCardId = :libraryCardId and " +
            "rb.dateOfBookReceiving = :dateOfBookReceiving and " +
            "rb.dateOfBookReturn = null")
    void returnBook(
            @Param("dateOfBookReturn") LocalDate dateOfBookReturn,
            @Param("dateOfBookReceiving") LocalDate dateOfBookReceiving,
            @Param("bookId") Long bookId,
            @Param("libraryCardId") Long libraryCardId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.bookId = :bookId and rb.libraryCardId = :libraryCardId " +
            "and rb.dateOfBookReturn = null")
    Optional<ReceivedBook> findReceivedBook(
            @Param("libraryCardId") Long libraryCardId,
            @Param("bookId") Long bookId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.libraryCardId = :libraryCardId and rb.dateOfBookReturn = null")
    List<ReceivedBook> findReceivedBookByLibraryCardId(
            @Param("libraryCardId") Long libraryCardId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.libraryCardId = :libraryCardId")
    List<ReceivedBook> findHistoryReceivedBookByLibraryCardId(
            @Param("libraryCardId") Long libraryCardId);

    @Query(value = "select rb from ReceivedBook rb " +
            "where rb.id >= :fromReceivedBookId and rb.id <= :toReceivedBookId and rb.dateOfBookReturn = null")
    List<ReceivedBook> findAllReceivedBook(
            @Param("fromReceivedBookId") Long fromReceivedBookId,
            @Param("toReceivedBookId") Long toReceivedBookId);

    @Query(value = "select rb from ReceivedBook rb where rb.dateOfBookReturn = null")
    List<ReceivedBook> findAllReceivedBookForSendEmailClient();

    void deleteByBookId(
            @Param("bookId") Long bookId);
}
