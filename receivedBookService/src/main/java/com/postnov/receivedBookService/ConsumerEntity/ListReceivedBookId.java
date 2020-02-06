package com.postnov.receivedBookService.ConsumerEntity;

import java.util.ArrayList;
import java.util.List;

public class ListReceivedBookId {

    private List<Long> receivedBookId = new ArrayList<>();

    public List<Long> getReceivedBookId() {
        return receivedBookId;
    }

    public void setReceivedBookId(List<Long> receivedBookId) {
        this.receivedBookId = receivedBookId;
    }

}
