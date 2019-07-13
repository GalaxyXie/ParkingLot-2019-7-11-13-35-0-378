package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {
    private int serialOfUsingParkingLot;
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public int getSerialOfUsingParkingLot() {
        return serialOfUsingParkingLot;
    }

    public void setSerialOfUsingParkingLot(int serialOfUsingParkingLot) {
        this.serialOfUsingParkingLot = serialOfUsingParkingLot;
    }
    @Override
    public Ticket park(Car car) {
        List<Integer> countOfParkingPlace=new ArrayList<>();
        for (ParkingLot i:this.getParkingLots()) {
            countOfParkingPlace.add(i.getNum());
        }
        int  Max=Collections.max(countOfParkingPlace);
        ParkingLot usingParkingLot=this.getParkingLots().get(countOfParkingPlace.indexOf(Max));
        this.setSerialOfUsingParkingLot(usingParkingLot.getSerialNumber());
        return  usingParkingLot.park(car);
    }
}
