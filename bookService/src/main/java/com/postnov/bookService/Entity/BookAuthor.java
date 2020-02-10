package com.postnov.bookService.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BookAuthor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private Long book_id;

    @Column
    private Long author_id;

    public BookAuthor() {
    }

    public BookAuthor(Long book_id, Long author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }
}
