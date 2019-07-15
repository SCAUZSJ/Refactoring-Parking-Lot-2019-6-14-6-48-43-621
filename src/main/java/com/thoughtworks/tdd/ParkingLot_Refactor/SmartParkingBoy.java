package com.thoughtworks.tdd.ParkingLot_Refactor;

import com.thoughtworks.tdd.Enum.FeedBack;
import com.thoughtworks.tdd.ParkingLot_Refactor.Interface.ParkingPerson;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmartParkingBoy extends ParkingBoy implements ParkingPerson {

    public SmartParkingBoy(Integer parkingBoyId , ParkingLot parkingLot) {
        super(parkingBoyId,parkingLot);
    }

    public SmartParkingBoy(Integer parkingBoyId ,List<ParkingLot> parkingLots) {
        super(parkingBoyId,parkingLots);
    }

    @Override
    public Ticket parking(Car car) {
        if(car.getCarId()==null) return null;
        Ticket ticket = null;
        ParkingLot parkingLot = chooseParkingLot();

        if(parkingLot==null){
            super.setErrorMsg(FeedBack.NotEnoughPosition.getMessage());
        }else{
            ticket = parkingLot.park(car);
            ticket.setOperatorId(this.getParkingBoyId());
        }
        return ticket;
    }
    @Override
    public ParkingLot chooseParkingLot() {
        List<ParkingLot> parkingLots = super.getParkingLots();
        List<ParkingLot> parkingLotList = parkingLots.stream().filter((pl)->{
            return pl.getCapacity()-pl.getCarList().size()>0;
        }).sorted(Comparator.comparing(pl->pl.getCapacity()-pl.getCarList().size())).collect(Collectors.toList());
        if(parkingLotList.size()>0){
            return parkingLotList.get(parkingLotList.size()-1);
        }
        return null;
    }
}