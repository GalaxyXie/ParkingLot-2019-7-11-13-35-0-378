package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SuperSmartParkingBoy extends SmartParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car) {
        List<Double> countOfParkingPlace=new ArrayList<>();
        for (ParkingLot i:this.getParkingLots()) {
            countOfParkingPlace.add((double)i.getNum()/i.getCapacity());
            System.out.println((double)i.getNum()/i.getCapacity());
        }
        double Max=Collections.max(countOfParkingPlace);
        ParkingLot usingParkingLot=this.getParkingLots().get(countOfParkingPlace.indexOf(Max));
        this.setSerialOfUsingParkingLot(usingParkingLot.getSerialNumber());
        return  usingParkingLot.park(car);

    }
}
