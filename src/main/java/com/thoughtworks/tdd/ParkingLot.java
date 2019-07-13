package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap parkingCarTicket;
    private int num=10;
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
            System.out.println("车位已满");
            return null;
        }
    }

    public Car getCar(Ticket ticket) {
        if(ticket.getTag().equals("not fetch")) {
            ticket.setFetchedMessage("is fetched");
            return (Car) this.getParkingCarTicket().get(ticket);
        }else{
            System.out.println("you have fetched car");
            return null;
        }

    }
}

