package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot=parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Ticket park(Car car) {
        return this.getParkingLot().park(car);
    }

    public Car fetch(Ticket ticket){

        try {
            Car car = parkingLot.getCar(ticket);
            System.out.println("I'm here1");
            return car;
        } catch (Exception e) {
            System.out.println("I'm here2");
            throw  e;
        }

    }
}
