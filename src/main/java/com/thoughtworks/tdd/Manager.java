package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manager {
    List<ParkingBoy> boys;
    ParkingLot parkingLotInManage;


    public Manager(List<ParkingBoy> boys, ParkingLot parkingLotInManage) {
        this.boys = boys;
        this.parkingLotInManage = parkingLotInManage;
    }

    public void addPrkingBoyToList(ParkingBoy parkingBoy) {
        this.getBoys().add(parkingBoy);
        ParkingLot parkingLot1 = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        this.getBoys().add(smartParkingBoy);

    }

    public Ticket specifyAParkingBoyParkCar(ParkingBoy parkingBoy, Car car) {
        if (this.getBoys().indexOf(parkingBoy) > -1)
            return parkingBoy.park(car);
        else
            return null;
    }

    public Car specifyAParkingBoyFetchCar(ParkingBoy parkingBoy, Ticket ticket) {
        if (this.getBoys().indexOf(parkingBoy) > -1)
            return parkingBoy.fetch(ticket);
        else
            return null;
    }

    public List<ParkingBoy> getBoys() {
        return boys;
    }

    public ParkingLot getParkingLotInManage() {
        return parkingLotInManage;
    }

    public Ticket park(Car car) {
        Ticket ticket = this.getParkingLotInManage().park(car);
        return ticket;

    }

    public Car fetch(Ticket ticket) {
        return this.getParkingLotInManage().getCar(ticket);
    }

    public String returnMessageToCustom(ParkingBoy parkingBoy) {

        return parkingBoy.passMessageToCustom();
    }
}
