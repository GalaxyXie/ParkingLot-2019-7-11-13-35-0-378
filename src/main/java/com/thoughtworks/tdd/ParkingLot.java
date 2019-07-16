package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    private int number = 10;
    private int capacity = 10;
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int number, int capacity, int serialNumber) {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
        this.number = number;
        this.capacity = capacity;
        this.serialNumber = serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
    }

    public HashMap getParkingCarTicket() {
        return parkingCarTicket;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        this.getParkingCarTicket().put(ticket, car);
        this.setNumber(this.getNumber()-1);
        return ticket;
    }

    public Car getCar(Ticket ticket) {
        Car car =(Car)parkingCarTicket.get(ticket);
        parkingCarTicket.remove(ticket);
        return car;

    }
}

