package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car)throws Exception {
        ParkingLot parkingLotCanParkCar=this.getParkingLots().stream().
                reduce((pre,cur)->pre.getNum()>=cur.getNum()?pre:cur).
                orElseThrow(() -> new Exception("Not enough position."));
        if(parkingLotCanParkCar.getNum()<=0)
            throw new Exception("Not enough position.");
        this.setUsingParkingLot(parkingLotCanParkCar);
        return  parkingLotCanParkCar.park(car);
    }
}
