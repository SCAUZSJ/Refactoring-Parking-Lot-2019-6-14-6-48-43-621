package com.thoughtworks.tdd.ParkingLot_Refactor;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoys = new ArrayList<>();

    public ServiceManager(Integer parkingBoyId,List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public ServiceManager(Integer parkingBoyId, ParkingLot parkingLot, List<ParkingBoy> parkingBoys) {
        super(parkingBoyId,parkingLot);
        this.parkingBoys = parkingBoys;
    }

    public ServiceManager(Integer parkingBoyId , List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingBoyId,parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        this.parkingBoys.add(parkingBoy);
    }

    public ParkingBoy getSpecifyParkingBoy(Integer parkingBoyId){
        ParkingBoy parkingBoy =null;
        for(ParkingBoy pb : this.parkingBoys){
            if(pb.getParkingBoyId().equals(parkingBoyId)){
                parkingBoy = pb;
                break;
            }
        }
        return parkingBoy;
    }

    //overload
    public Ticket parking(Integer parkingBoyId, Car car) {
        Ticket ticket =null;
        if(parkingBoyId!=null) {
            ParkingBoy parkingBoy = getSpecifyParkingBoy(parkingBoyId);
            if(parkingBoy!=null){
                 ticket = parkingBoy.parking(car);
                if(ticket == null){
                    super.setServiceFeedBack(parkingBoy.getServiceFeedBack());
                }
            }

        }
        return ticket;
    }
    //overload
    public Car redeemCar(Integer parkingBoyId, Ticket ticket) {
        Car car = null;
        if(parkingBoyId!=null){
            ParkingBoy parkingBoy = getSpecifyParkingBoy(parkingBoyId);
            if(parkingBoy!=null){
                car = parkingBoy.redeemCar(ticket);
                if(car == null){
                    super.setServiceFeedBack(parkingBoy.getServiceFeedBack());
                }
            }
        }
        return car;
    }
}
