package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Float.parseFloat;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car)throws Exception {
        ParkingLot parkingLotCanParkCar=this.getParkingLots().stream().
                reduce((pre,cur)->(pre.getNum()*1.0/pre.getCapacity())>=(cur.getNum()*1.0/cur.getCapacity())?pre:cur).
                orElseThrow(() -> new Exception("Not enough position."));
        if(parkingLotCanParkCar.getNum()<=0)
            throw new Exception("Not enough position.");
        this.setUsingParkingLot(parkingLotCanParkCar);
        return  parkingLotCanParkCar.park(car);
    }
}
