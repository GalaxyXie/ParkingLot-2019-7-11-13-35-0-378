package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = null;
//    private ParkingLot usingParkingLot=null;
//
//    public ParkingLot getUsingParkingLot() {
//        return usingParkingLot;
//    }
//
//    public void setUsingParkingLot(ParkingLot usingParkingLot) {
//        this.usingParkingLot=usingParkingLot;
//    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public String passMessageToCustom() {

        if (this.searchAvailableParkingLot() == null) return "Not enough position.";
        else {
            return this.searchAvailableParkingLot().getMessageToCustom();
        }
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingLot searchAvailableParkingLot() {
        for (ParkingLot i : this.getParkingLots()) {
            if (i.getNum() > 0)
                return i;
        }

        return null;
    }

    public Ticket park(Car car) {
        for (ParkingLot i : this.getParkingLots()) {
            Ticket ticket = i.park(car);
            if (ticket == null) {
                continue;
            } else {
                return ticket;
            }
        }
        return null;
    }

    public Car fetch(Ticket ticket) {
        for (ParkingLot i : parkingLots) {
            Car car = i.getCar(ticket);
            if (car != null) {
                return car;
            }
        }
        return null;

    }

}