package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    private int num = 10;
    private int capacity = 10;
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int num, int capacity, int serialNumber) {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
        this.num = num;
        this.capacity = capacity;
        this.serialNumber = serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
    }

    public HashMap getParkingCarTicket() {
        return parkingCarTicket;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket("not fetch");
        this.getParkingCarTicket().put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket) {
        Car car =(Car)parkingCarTicket.get(ticket);
        parkingCarTicket.remove(ticket);
        return car;

    }
}

