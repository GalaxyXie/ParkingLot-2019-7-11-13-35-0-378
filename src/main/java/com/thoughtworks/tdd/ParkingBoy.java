package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy extends Parker{

    public ParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    public Ticket park(Car car)throws Exception {
        ParkingLot parkingLotCanParkCar=this.getParkingLots().stream().
                filter(parkingLot -> parkingLot.getNum()>0)
                .findFirst().orElseThrow(() -> new Exception("Not enough position."));
        return  parkingLotCanParkCar.park(car);
    }

}