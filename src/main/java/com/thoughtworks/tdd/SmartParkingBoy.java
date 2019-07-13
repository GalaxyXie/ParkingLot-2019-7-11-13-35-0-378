package com.thoughtworks.tdd;

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
       List<Integer> countOfParkingPlace=this.getParkingLots().stream().mapToInt(i->i.getNum()).boxed().collect(Collectors.toList());
        int Max = Collections.max(countOfParkingPlace);
        for (ParkingLot i:this.getParkingLots()
             ) {
            if(i.getNum()==Max){
                i.park(car);
                this.setSerialOfUsingParkingLot(i.getSerialNumber());
            }
        }
        return null;
    }
}
