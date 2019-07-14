package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ManagerTest {
    @Test
    public void should_specify_a_Boy_to_park_car_in_parking_when_parkingLot_is_in_manage(){
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
        Manager manager=new Manager(parkingBoys,parkingLot1);
        //add a Boy
        manager.addPrkingBoyToList(smartParkingBoy);

        Ticket ticket = manager.specifyAParkingBoyParkCar(parkingBoy,car);
        Car fetchedNoCar = manager.specifyAParkingBoyFetchCar(smartParkingBoy, ticket);
        Car fetchedCar=manager.specifyAParkingBoyFetchCar(parkingBoy,ticket);


        //then
        assertSame(null,fetchedNoCar);
        assertSame(car,fetchedCar);
    }
    @Test
    public void should_return_Car_when_Manager_fetched_car_when_parkingLot_is_in_manage(){
        //given
        Car car=new Car();
        ParkingLot parkingLot1=new ParkingLot(10,10,1);

        List<ParkingLot> parkingLotList1=new ArrayList<>();
        List<ParkingBoy> parkingBoys=new ArrayList<>();
        parkingLotList1.add(parkingLot1);



        //parkingLot1 in this manager‘s management
        Manager manager=new Manager(parkingBoys,parkingLot1);

        //when
        Ticket ticket=manager.park(car);
        Car fetchedCar = manager.fetch(ticket);

        //then
        assertSame(car,fetchedCar);
    }
    @Test
    public void should_return_Message_to_custom_when_parkingBoy_does_not_park_car_success(){
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
        Manager manager=new Manager(parkingBoys,parkingLot1);
        Ticket wrontTicket=new Ticket("not fetch");
        //when
        //ticket is null because parking boy did not park success
        Ticket ticket = manager.specifyAParkingBoyParkCar(parkingBoy,car);
        String messageFromParkingBoy = manager.returnMessageToCustom(parkingBoy);
        manager.specifyAParkingBoyFetchCar(parkingBoy,ticket);
        String messageFromParkingBoyFetchCar = manager.returnMessageToCustom(parkingBoy);
        manager.specifyAParkingBoyFetchCar(parkingBoy,wrontTicket);
        String messageFromParkingBoyFetchCarAgain = manager.returnMessageToCustom(parkingBoy);
        //then
        assertSame("Not enough position.",messageFromParkingBoy);
        assertSame("Please provide your parking ticket.",messageFromParkingBoyFetchCar);
        assertSame("Unrecognized parking ticket.",messageFromParkingBoyFetchCarAgain);
    }

}
