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

        parkingLot1.setNumber(0);
        parkingLot2.setNumber(0);

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

    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_null() throws Exception {
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
        Executable executable = () -> {
            Car fetchedCar = parkingBoy.fetch(null);
        };
        //then
        Exception exception = assertThrows(Exception.class, executable);
        assertThat(exception.getMessage(), is("Please provide your parking ticket."));

    }

    @Test
    public void should_return_Message_to_custom_when_parking_lot_is_full() throws Exception {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingLot1.setNumber(0);
        parkingLot2.setNumber(1);
        //There is only one parking space in the parking lot
        Ticket ticket = parkingBoy.park(car);
        //when
        Executable executable = () -> {
            parkingBoy.park(car);
        };
        //then
        Exception exception = assertThrows(Exception.class, executable);
        assertThat(exception.getMessage(), is("Not enough position."));
    }

    @Test
    public void should_park_car_in_parkingLot2_when_parkingLot1_is_full() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        parkingLot1.setNumber(0);
        parkingLot2.setNumber(1);

        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

}
