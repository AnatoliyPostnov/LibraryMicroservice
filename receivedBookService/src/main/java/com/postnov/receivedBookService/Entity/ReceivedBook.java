package com.postnov.receivedBookService.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ReceivedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate dateOfBookReceiving = LocalDate.now();

    @Column
    private LocalDate dateOfBookReturn;

    @Column
    private Long bookId;

    @Column
    private Long libraryCardId;

    public ReceivedBook() {
    }

    public ReceivedBook(Long id,
                        Long bookId,
                        Long libraryCardId) {
        this.id = id;
        this.bookId = bookId;
        this.libraryCardId = libraryCardId;
    }

    public Long getId() {
        return id;
    }

    public ReceivedBook setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDateOfBookReceiving() {
        return dateOfBookReceiving;
    }

    public ReceivedBook setDateOfBookReceiving(LocalDate dateOfBookReceiving) {
        this.dateOfBookReceiving = dateOfBookReceiving;
        return this;
    }

    public LocalDate getDateOfBookReturn() {
        return dateOfBookReturn;
    }

    public ReceivedBook setDateOfBookReturn(LocalDate dateOfBookReturn) {
        this.dateOfBookReturn = dateOfBookReturn;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public ReceivedBook setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public Long getLibraryCardId() {
        return libraryCardId;
    }

    public ReceivedBook setLibraryCardId(Long libraryCardId) {
        this.libraryCardId = libraryCardId;
        return this;
    }

}
