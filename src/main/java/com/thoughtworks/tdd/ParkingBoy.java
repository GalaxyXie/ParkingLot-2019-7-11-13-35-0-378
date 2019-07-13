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
            Car car = parkingLot.getCar(ticket);
            return car;
    }
}
