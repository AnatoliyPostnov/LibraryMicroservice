package com.postnov.receivedBookService.Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListReceivedBookIdDto implements Serializable {

    private List<Long> receivedBookId = new ArrayList<>();

    public ListReceivedBookIdDto() {
    }

    public List<Long> getReceivedBookId() {
        return receivedBookId;
    }

    public void setReceivedBookId(List<Long> receivedBookId) {
        this.receivedBookId = receivedBookId;
    }

}
