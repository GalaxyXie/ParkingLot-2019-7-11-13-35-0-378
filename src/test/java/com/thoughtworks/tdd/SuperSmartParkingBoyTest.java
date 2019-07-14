package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperSmartParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_gei_it_back() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        Ticket ticket = superSmartParkingBoy.park(car);
        Car fetchedCar = superSmartParkingBoy.fetch(ticket);
        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_return_multiple_cars_when_park_cars_to_parking_lot_then_get_them_back() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        Ticket firstTicket = SuperSmartParkingBoy.park(firstCar);
        Ticket secondTicket = SuperSmartParkingBoy.park(secondCar);
        Car fetchedFirstCar = SuperSmartParkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = SuperSmartParkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = SuperSmartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket("not fetch");
        //when
        Car fetchedCar = SuperSmartParkingBoy.fetch(wrongTicket);
        //then
        assertSame(null, fetchedCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_used() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        Ticket ticket = SuperSmartParkingBoy.park(car);
        Car fetchedCar = SuperSmartParkingBoy.fetch(ticket);
        Car fetchedAgainCar = SuperSmartParkingBoy.fetch(ticket);
        //then
        assertSame(null, fetchedAgainCar);
    }

    @Test
    public void should_not_park_car_when_parkingLot_is_full() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        parkingLot1.setNum(0);
        parkingLot2.setNum(0);
        //There is only one parking space in the parking lot
        Ticket ticket = SuperSmartParkingBoy.park(car);

        Ticket anothorTicket = SuperSmartParkingBoy.park(car);

        //then
        assertSame(null, anothorTicket);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_wrong() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = SuperSmartParkingBoy.park(car);
        Ticket wrongTicket = new Ticket("not fetch");
        //when
        Car fetchedCar = SuperSmartParkingBoy.fetch(wrongTicket);
        String returnMessage = SuperSmartParkingBoy.passMessageToCustom();
        //then
        assertSame("Unrecognized parking ticket.", returnMessage);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_null() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = SuperSmartParkingBoy.park(car);

        //when
        Car fetchedCar = SuperSmartParkingBoy.fetch(null);
        String returnMessage = SuperSmartParkingBoy.passMessageToCustom();
        //then
        assertSame("Please provide your parking ticket.", returnMessage);
    }

    @Test
    public void should_return_Message_to_custom_when_parking_lot_is_full() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(0, 10, 1);
        ParkingLot parkingLot2 = new ParkingLot(0, 10, 2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when
        Ticket ticket = SuperSmartParkingBoy.park(car);
        Ticket anothorTicket = SuperSmartParkingBoy.park(car);
        String returnMessage = SuperSmartParkingBoy.passMessageToCustom();

        //then
        assertSame("Not enough position.", returnMessage);
    }

    @Test
    public void should_park_car_in_parkingLot2_when_parkingLot1_is_full() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(0, 10, 1);
        ParkingLot parkingLot2 = new ParkingLot(1, 10, 2);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SuperSmartParkingBoy SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //when


        Ticket ticket = SuperSmartParkingBoy.park(car);

        Car fetchedCar = SuperSmartParkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_park_car_in_parkingLot_which_has_a_larger_available_position_rate() {
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
        int serialNumber = SuperSmartParkingBoy.getSerialOfUsingParkingLot();
        Car fetchedCar = SuperSmartParkingBoy.fetch(ticket);

        //then
        assertSame(1, serialNumber);
    }

}
