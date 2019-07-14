package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    private int num = 10;
    private int capacity = 10;
    private String messageToCustom;
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingLot(int num, int capacity, int serialNumber) {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
        ;
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

    public String getMessageToCustom() {
        return messageToCustom;
    }

    public void setMessageToCustom(String messageToCustom) {
        this.messageToCustom = messageToCustom;
    }

    public ParkingLot() {
        this.parkingCarTicket = new HashMap<Ticket, Car>();
    }

    public HashMap getParkingCarTicket() {
        return parkingCarTicket;
    }

    public Ticket park(Car car) {
        if (this.getNum() > 0) {
            this.setNum(this.getNum() - 1);
            System.out.println("park success");
            this.setMessageToCustom("park success");
            Ticket ticket = new Ticket("not fetch");
            this.getParkingCarTicket().put(ticket, car);
            return ticket;
        } else {
            this.setMessageToCustom("Not enough position.");
            System.out.println("Not enough position.");
            return null;
        }
    }

    public Car getCar(Ticket ticket) {

        if (ticket == null) {
            this.setMessageToCustom("Please provide your parking ticket.");
            System.out.println("Please provide your parking ticket.");
            return null;
        }
        if (ticket.getTag().equals("not fetch") && this.getParkingCarTicket().get(ticket) != null) {

            ticket.setFetchedMessage("is fetched");
            return (Car) this.getParkingCarTicket().get(ticket);
        } else {
            this.setMessageToCustom("Unrecognized parking ticket.");
            System.out.println("Unrecognized parking ticket.");
            return null;
        }

    }
}

