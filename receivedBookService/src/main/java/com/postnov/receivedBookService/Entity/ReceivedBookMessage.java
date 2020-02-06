package com.postnov.receivedBookService.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ReceivedBookMessage {

    @javax.persistence.Id
    @GeneratedValue
    Long Id;

    @Column
    @Lob
    String message;

    public ReceivedBookMessage() {
    }

    public ReceivedBookMessage(String message) {
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
        ReceivedBookMessage that = (ReceivedBookMessage) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, message);
    }
}
