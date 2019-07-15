package com.thoughtworks.tdd;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_gei_it_back() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        //then
        assertEquals(car, fetchedCar);
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        // when
        Executable executable = () -> {
            parkingBoy.fetch(wrongTicket);
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when

        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);
        Executable executable = () -> {
            parkingBoy.fetch(ticket);
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingLot1.setNum(0);
        parkingLot2.setNum(0);

        // when
        Executable executable = () -> {
            Ticket ticket = parkingBoy.park(car);
        };

        // then
        assertThrows(Exception.class, executable);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_wrong() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        //when
        Executable executable = () -> {
            Car fetchedCar = parkingBoy.fetch(wrongTicket);
        };
        //then
        Exception exception = assertThrows(Exception.class, executable);
        assertThat(exception.getMessage(), is("Unrecognized parking ticket."));
    }
/*
    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_null() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(car);

        //when
        Car fetchedCar = parkingBoy.fetch(null);
        String returnMessage = parkingBoy.passMessageToCustom();
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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(1);
        //There is only one parking space in the parking lot
        Ticket ticket = parkingBoy.park(car);
        Ticket anothorTicket = parkingBoy.park(car);


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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(1);

        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);

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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(4);
        parkingLot2.setNum(8);

        Ticket ticket = smartParkingBoy.park(car);
        int serialNumber = smartParkingBoy.getSerialOfUsingParkingLot();
        Car fetchedCar = smartParkingBoy.fetch(ticket);

        //then
        assertSame(2, serialNumber);
    }*/

}
