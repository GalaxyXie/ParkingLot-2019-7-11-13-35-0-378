package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {


    @Test
    public void should_park_car_in_parkingLot_which_has_a_larger_available_position_rate() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(3, 10, 1);
        ParkingLot parkingLot2 = new ParkingLot(4, 15, 2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when

        Ticket ticket = SuperSmartParkingBoy.park(car);
        int serialNumber = SuperSmartParkingBoy.getUsingParkingLot().getSerialNumber();
        Car fetchedCar = SuperSmartParkingBoy.fetch(ticket);

        //then
        assertSame(1, serialNumber);
    }

}
