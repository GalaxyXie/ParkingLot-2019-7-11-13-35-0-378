package com.thoughtworks.tdd;

import java.util.List;

public class Manager extends Parker{
    private List<ParkingBoy> parkingBoys;

    public Manager(List<ParkingBoy> parkingBoys,List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public void addPrkingBoyToList(ParkingBoy parkingBoy) {
        this.parkingBoys.add(parkingBoy);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ParkingBoy getParkingBoy(int index) {
        return parkingBoys.get(index);
    }

    public Ticket specifyAParkingBoyParkCar(ParkingBoy parkingBoy, Car car) throws Exception {
        return parkingBoy.park(car);
    }

    public Car specifyAParkingBoyFetchCar(ParkingBoy parkingBoy, Ticket ticket) throws Exception {
       return parkingBoy.fetch(ticket);
    }

    @Override
    public Ticket park(Car car)throws Exception {
        ParkingLot parkingLotCanParkCar=this.getParkingLots().stream().
                filter(parkingLot -> parkingLot.getNumber()>0)
                .findFirst().orElseThrow(() -> new Exception("Not enough position."));
        return  parkingLotCanParkCar.park(car);
    }
}
