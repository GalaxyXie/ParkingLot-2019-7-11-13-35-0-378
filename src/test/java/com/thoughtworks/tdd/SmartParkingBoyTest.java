package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_gei_it_back() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        Ticket ticket = SmartParkingBoy.park(car);
        Car fetchedCar = SmartParkingBoy.fetch(ticket);
        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_multiple_cars_when_park_cars_to_parking_lot_then_get_them_back() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        Ticket firstTicket = SmartParkingBoy.park(firstCar);
        Ticket secondTicket = SmartParkingBoy.park(secondCar);
        Car fetchedFirstCar = SmartParkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = SmartParkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = SmartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        // when
        Executable executable = () -> {
            SmartParkingBoy.fetch(wrongTicket);
        };

        // then
        assertThrows(Exception.class, executable);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_used() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        Ticket ticket = SmartParkingBoy.park(car);
        Car fetchedCar = SmartParkingBoy.fetch(ticket);
        // when
        Executable executable = () -> {
            SmartParkingBoy.fetch(ticket);
        };

        // then
        assertThrows(Exception.class, executable);
    }

    @Test
    public void should_not_park_car_when_parkingLot_is_full() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(0);
        //There is only one parking space in the parking lot

        // when
        Executable executable = () -> {
            Ticket ticket = SmartParkingBoy.park(car);
        };

        // then
        assertThrows(Exception.class, executable);
    }
/*
    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_wrong() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = SmartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket("not fetch");
        //when
        Car fetchedCar = SmartParkingBoy.fetch(wrongTicket);
        String returnMessage = SmartParkingBoy.passMessageToCustom();
        //then
        assertSame("Unrecognized parking ticket.", returnMessage);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_null() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = SmartParkingBoy.park(car);

        //when
        Car fetchedCar = SmartParkingBoy.fetch(null);
        String returnMessage = SmartParkingBoy.passMessageToCustom();
        //then
        assertSame("Please provide your parking ticket.", returnMessage);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_lot_is_full() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(1);
        //There is only one parking space in the parking lot
        Ticket ticket = SmartParkingBoy.park(car);
        Ticket anothorTicket = SmartParkingBoy.park(car);
        String returnMessage = SmartParkingBoy.passMessageToCustom();

        //then
        assertSame("Not enough position.", returnMessage);
    }

    @Test
    public void should_park_car_in_parkingLot2_when_parkingLot1_is_full() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(1);

        Ticket ticket = SmartParkingBoy.park(car);
        Car fetchedCar = SmartParkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_park_car_in_parkingLot_which_has_more_parking_place() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setSerialNumber(1);
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.setSerialNumber(2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy SmartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(4);
        parkingLot2.setNum(8);

        Ticket ticket = SmartParkingBoy.park(car);
        int serialNumber = SmartParkingBoy.getSerialOfUsingParkingLot();
        Car fetchedCar = SmartParkingBoy.fetch(ticket);

        //then
        assertSame(2, serialNumber);
    }
*/
}
