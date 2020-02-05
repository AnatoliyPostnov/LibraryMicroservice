package com.postnov.libraryOrchestrator.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AddBookJson implements JsonMessage {

    @Id
    @GeneratedValue
    Long Id;

    @Column
    @Lob
    String message;

    public AddBookJson(){}

    public AddBookJson(String message) {
        this.message = message;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddBookJson that = (AddBookJson) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, message);
    }
}
