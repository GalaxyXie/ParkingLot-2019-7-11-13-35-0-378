package com.thoughtworks.tdd;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    @Override
    public Ticket park(Car car)throws Exception {
        ParkingLot parkingLotCanParkCar=this.getParkingLots().stream().
                reduce((pre,cur)->(pre.getNumber()*1.0/pre.getCapacity())>=(cur.getNumber()*1.0/cur.getCapacity())?pre:cur).
                orElseThrow(() -> new Exception("Not enough position."));
        if(parkingLotCanParkCar.getNumber()<=0)
            throw new Exception("Not enough position.");
        this.setUsingParkingLot(parkingLotCanParkCar);
        return  parkingLotCanParkCar.park(car);
    }
}
