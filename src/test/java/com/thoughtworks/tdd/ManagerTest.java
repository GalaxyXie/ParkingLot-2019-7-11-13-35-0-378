package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTest {
    @Test
    public void should_specify_a_Boy_to_park_car_in_parking_when_parkingLot_is_in_manage() throws Exception {
        //given
        Car car=new Car();
        ParkingLot parkingLot1=new ParkingLot(10,10,1);
        ParkingLot parkingLot2=new ParkingLot(10,10,1);
        List<ParkingLot> parkingLotList1=new ArrayList<>();
        List<ParkingLot> parkingLotList2=new ArrayList<>();
        List<ParkingBoy> parkingBoys=new ArrayList<>();
        parkingLotList1.add(parkingLot1);
        parkingLotList2.add(parkingLot2);
        ParkingBoy parkingBoy=new ParkingBoy(parkingLotList1);
        SmartParkingBoy smartParkingBoy=new SmartParkingBoy(parkingLotList2);
        //List Only has boy1
        parkingBoys.add(parkingBoy);

        //parkingLot1 in this manager‘s management
        Manager manager=new Manager(parkingBoys,parkingLotList1);
        //add a Boy
        manager.addPrkingBoyToList(smartParkingBoy);

        Ticket ticket = manager.specifyAParkingBoyParkCar(parkingBoy,car);

        Car fetchedCar=manager.specifyAParkingBoyFetchCar(parkingBoy,ticket);

        assertSame(car,fetchedCar);

    }
    @Test
    public void should_return_Car_when_Manager_fetched_car_when_parkingLot_is_in_manage() throws Exception {
        //given
        Car car=new Car();
        ParkingLot parkingLot1=new ParkingLot(10,10,1);

        List<ParkingLot> parkingLotList1=new ArrayList<>();
        List<ParkingBoy> parkingBoys=new ArrayList<>();
        parkingLotList1.add(parkingLot1);



        //parkingLot1 in this manager‘s management
        Manager manager=new Manager(parkingBoys,parkingLotList1);

        //when
        Ticket ticket=manager.park(car);
        Car fetchedCar = manager.fetch(ticket);

        //then
        assertSame(car,fetchedCar);
    }
    @Test
    public void should_return_Message_to_custom_when_parkingBoy_does_not_park_car_success() throws Exception {
        //given
        Car car=new Car();
        ParkingLot parkingLot1=new ParkingLot(0,10,1);
        ParkingLot parkingLot2=new ParkingLot(10,10,1);
        List<ParkingLot> parkingLotList1=new ArrayList<>();
        List<ParkingLot> parkingLotList2=new ArrayList<>();
        List<ParkingBoy> parkingBoys=new ArrayList<>();
        parkingLotList1.add(parkingLot1);
        parkingLotList2.add(parkingLot2);
        ParkingBoy parkingBoy=new ParkingBoy(parkingLotList1);
        SmartParkingBoy smartParkingBoy=new SmartParkingBoy(parkingLotList2);
        //List Only has boy1
        parkingBoys.add(parkingBoy);
        parkingBoys.add(smartParkingBoy);
        //parkingLot1 in this manager‘s management
        Manager manager=new Manager(parkingBoys,parkingLotList1);
        Ticket wrontTicket=new Ticket();

        //when
        Executable executable = () -> {
            manager.specifyAParkingBoyParkCar(parkingBoy,car);
        };
        //then
        Exception exception = assertThrows(Exception.class, executable);
        assertThat(exception.getMessage(), is("Not enough position."));
        //when
        Executable executable1 = () -> {
            manager.specifyAParkingBoyFetchCar(parkingBoy,null);
        };
        //then
        Exception exception1 = assertThrows(Exception.class, executable1);
        assertThat(exception1.getMessage(), is("Please provide your parking ticket."));
        //when
        Executable executable2 = () -> {
            manager.specifyAParkingBoyFetchCar(parkingBoy,wrontTicket);
        };
        //then
        Exception exception2 = assertThrows(Exception.class, executable2);
        assertThat(exception2.getMessage(), is("Unrecognized parking ticket."));
    }

}
