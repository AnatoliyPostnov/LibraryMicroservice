package com.postnov.libraryOrchestrator.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AddLibraryCardJson implements Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String message;

    public AddLibraryCardJson() {
    }

    public AddLibraryCardJson(String message) {
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
        AddLibraryCardJson that = (AddLibraryCardJson) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, message);
    }
}
