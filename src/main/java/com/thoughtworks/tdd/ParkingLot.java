package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    public ParkingLot() {
        this.parkingCarTicket = new HashMap<Ticket,Car>();
    }

    public HashMap getParkingCarTicket() {
        return parkingCarTicket;
    }

    public Ticket park(Car car) {
        Ticket ticket=new Ticket();
        this.getParkingCarTicket().put(ticket,car);
        return ticket;
    }

    public Car getCar(Ticket ticket) {
        return (Car)this.getParkingCarTicket().get(ticket);
    }
}
