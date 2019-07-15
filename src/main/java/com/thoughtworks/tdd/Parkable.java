package com.thoughtworks.tdd;

public interface Parkable {
    public Ticket park(Car car) throws Exception;

    public Car fetch(Ticket ticket) throws Exception;
}
