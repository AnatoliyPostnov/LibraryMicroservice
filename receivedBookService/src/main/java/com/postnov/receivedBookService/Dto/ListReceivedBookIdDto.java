package com.postnov.receivedBookService.Dto;

import java.util.ArrayList;
import java.util.List;

public class ListReceivedBookIdDto {

    private List<Long> receivedBookId = new ArrayList<>();

    public ListReceivedBookIdDto(){}

    public List<Long> getReceivedBookId() {
        return receivedBookId;
    }

    public void setReceivedBookId(List<Long> receivedBookId) {
        this.receivedBookId = receivedBookId;
    }

}
