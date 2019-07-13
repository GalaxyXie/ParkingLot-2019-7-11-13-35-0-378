package com.thoughtworks.tdd;

public class Ticket {
    String fetchedMessage;

    public Ticket(String fetchedMessage) {
        this.fetchedMessage = fetchedMessage;
    }

    public void setFetchedMessage(String fetchedMessage) {
        this.fetchedMessage = fetchedMessage;
    }

    public String getTag() {
        return fetchedMessage;
    }
}
