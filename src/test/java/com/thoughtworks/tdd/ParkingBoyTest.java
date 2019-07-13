package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_gei_it_back(){
        //given
        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        //when
        Ticket ticket=parkingBoy.park(car);
        Car fetchedCar= parkingBoy.fetch(ticket);
        //then
        assertSame(car,fetchedCar);
    }
    @Test
    public void should_return_multiple_cars_when_park_cars_to_parking_lot_then_get_them_back(){
        //given
        Car firstCar=new Car();
        Car secondCar=new Car();
        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        //when
        Ticket firstTicket=parkingBoy.park(firstCar);
        Ticket secondTicket=parkingBoy.park(secondCar);
        Car fetchedFirstCar= parkingBoy.fetch(firstTicket);
        Car fetchedSecondCar= parkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar,fetchedFirstCar);
        assertSame(secondCar,fetchedSecondCar);
    }
    @Test
    public void should_not_fetch_car_when_ticket_is_wrong(){
        //given
        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        Ticket ticket=parkingBoy.park(car);
        Ticket wrongTicket=new Ticket("not fetch");
        //when
        Car fetchedCar= parkingBoy.fetch(wrongTicket);
        //then
        assertSame(fetchedCar,null);
    }
    @Test
    public void should_not_fetch_car_when_ticket_is_used(){
        //given
        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        //when
        Ticket ticket=parkingBoy.park(car);
        Car fetchedCar= parkingBoy.fetch(ticket);
        Car fetchedAgainCar= parkingBoy.fetch(ticket);
        //then
        assertSame(fetchedAgainCar,null);
    }
    @Test
    public void should_not_park_car_when_parkingLot_is_full(){
        //given
        Car car=new Car();

        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        //when
        parkingLot.setNum(1);
        //There is only one parking space in the parking lot
        Ticket ticket=parkingBoy.park(car);
        Ticket anothorTicket=parkingBoy.park(car);

        //then
        assertSame(anothorTicket,null);
    }
    @Test
    public void should_return_Message_to_custom_when_parking_tiket_is_wrong(){
        //given
        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot();
        ParkingBoy parkingBoy=new ParkingBoy(parkingLot);
        Ticket ticket=parkingBoy.park(car);
        Ticket wrongTicket=new Ticket("not fetch");
        //when
        Car fetchedCar= parkingBoy.fetch(wrongTicket);
        String returnMessage=parkingLot.getMessageToCustom();
        //then
        assertSame(returnMessage,"Unrecognized parking ticket.");
    }
}
