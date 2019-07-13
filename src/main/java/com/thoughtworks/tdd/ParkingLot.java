package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    private int num=10;
    private String messageToCustom;
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
        this.parkingCarTicket = new HashMap<Ticket,Car>();
    }

    public HashMap getParkingCarTicket() {
        return parkingCarTicket;
    }

    public Ticket park(Car car) {
        if (this.getNum()>0){
            this.setNum(this.getNum()-1);
            Ticket ticket=new Ticket("not fetch");
            this.getParkingCarTicket().put(ticket,car);
            return ticket;
        }else{
            this.setMessageToCustom("ParkingLot is full");
            System.out.println("ParkingLot is full");
            return null;
        }
    }

    public Car getCar(Ticket ticket) {
        if(ticket.getTag().equals("not fetch")&&this.getParkingCarTicket().get(ticket)!=null) {
            ticket.setFetchedMessage("is fetched");
            return (Car) this.getParkingCarTicket().get(ticket);
        }else{
            this.setMessageToCustom("Unrecognized parking ticket.");
            System.out.println("Unrecognized parking ticket.");
            return null;
        }

    }
}

