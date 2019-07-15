package com.thoughtworks.tdd;

import java.util.List;

abstract public class Parker implements Parkable{
    private List<ParkingLot> parkingLots = null;
    //private ParkingLot usingParkingLot=null;

    public Parker(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Car fetch(Ticket ticket) throws Exception{
        if(ticket==null)
            throw new Exception("Please provide your parking ticket.");
        ParkingLot parkingLotCanFindCar=parkingLots.stream().
                filter(parkingLot -> parkingLot.getParkingCarTicket().containsKey(ticket))
                .findFirst().orElseThrow(() -> new Exception("Unrecognized parking ticket."));
       return  parkingLotCanFindCar.getCar(ticket);
    }
}
