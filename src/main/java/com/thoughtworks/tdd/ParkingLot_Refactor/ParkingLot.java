package com.thoughtworks.tdd.ParkingLot_Refactor;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {


    private Integer parkingLotId;
    private List<Car> carList;
    private int capacity =10;


    public ParkingLot(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
        carList = new ArrayList<>();
    }

    public List<Car> getCarList() {
        return carList;
    }

    public ParkingLot(Integer parkingLotId,int capacity) {
        this.parkingLotId = parkingLotId;
        this.capacity = capacity;
        this.carList = new ArrayList<>();
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }


    public Ticket park(Car car){
        if(this.carList.size()>=this.capacity){
            return null;
        }
        this.carList.add(car);
        Ticket ticket = new Ticket(car.getCarId());
        ticket.setParkingLotId(this.parkingLotId);
        return ticket;
    }

    public Car getCar(Integer carId) {
        if(carId==null) return  null;
        for(int i=0;i<carList.size();i++){
            if(carList.get(i).getCarId().equals(carId)){
                Car car = carList.get(i);
                carList.remove(i);
                return car;
            }
        }
        return null;
    }
    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
