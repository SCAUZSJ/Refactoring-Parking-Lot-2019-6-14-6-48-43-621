package com.thoughtworks.tdd.ParkingLot_Refactor.Interface;
import com.thoughtworks.tdd.ParkingLot_Refactor.Car;
import com.thoughtworks.tdd.ParkingLot_Refactor.ParkingLot;
import com.thoughtworks.tdd.ParkingLot_Refactor.Ticket;

public interface ParkingPerson {

    Ticket parking(Car car);
    Car fetchCar(Ticket ticket);
    ParkingLot chooseParkingLot();
}
