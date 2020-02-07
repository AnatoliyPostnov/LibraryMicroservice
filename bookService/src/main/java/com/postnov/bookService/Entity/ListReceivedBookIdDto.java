package com.postnov.bookService.Entity;

import java.util.List;

public class ListReceivedBookIdDto {

    private List<Long> receivedBookId;

    public ListReceivedBookIdDto(List<Long> receivedBookId) {
        this.receivedBookId = receivedBookId;
    }

    public List<Long> getReceivedBookId() {
        return receivedBookId;
    }

    public void setReceivedBookId(List<Long> receivedBookId) {
        this.receivedBookId = receivedBookId;
    }

}
