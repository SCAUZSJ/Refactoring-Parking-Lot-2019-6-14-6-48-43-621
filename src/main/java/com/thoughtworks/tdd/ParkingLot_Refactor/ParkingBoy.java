package com.thoughtworks.tdd.ParkingLot_Refactor;

import com.thoughtworks.tdd.ParkingLot_Refactor.Enum.FeedBack;
import com.thoughtworks.tdd.ParkingLot_Refactor.Interface.ParkingPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingBoy implements ParkingPerson {

    private Integer parkingBoyId;
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private String serviceFeedBack;

    public ParkingBoy(Integer parkingBoyId, ParkingLot parkingLot) {
        this.parkingBoyId = parkingBoyId;
        this.parkingLots.add(parkingLot);
    }
    public ParkingBoy(Integer parkingBoyId,List<ParkingLot> parkingLots) {
        this.parkingBoyId = parkingBoyId;
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }



    public Ticket parking(Car car){
        if(car.getCarId()==null) return null;
        ParkingLot parkingLot =chooseParkingLot();
        Ticket ticket =null;
        if(parkingLot!=null){
            ticket = parkingLot.park(car);
        }
        if(ticket == null){
            this.serviceFeedBack = FeedBack.NotEnoughPosition.getMessage();
        }else{
            ticket.setOperatorId(this.parkingBoyId);
        }
        return ticket;
    }
    public Car fetchCar(Ticket ticket){

        System.out.println(ticket);
        if(ticket == null) {
            this.serviceFeedBack =FeedBack.PleaseProvide.getMessage();
            return null;
        }
        if(!ticket.getState()){
            this.serviceFeedBack =FeedBack.UnrecognizedTicket.getMessage();
            return null;
        }
        Car car = null;
        if(ticket.getParkingLotId()!=null) {
            for (int i = 0; i < parkingLots.size(); i++) {
                if (ticket.getParkingLotId().equals(parkingLots.get(i).getParkingLotId())) {
                    car = parkingLots.get(i).getCar(ticket.getCarId());
                    ticket.setOperatorId(this.parkingBoyId);
                    break;
                }
            }
        }
        if(car == null){
            this.serviceFeedBack =FeedBack.UnrecognizedTicket.getMessage();
        }
        return car;
    }

    @Override
    public ParkingLot chooseParkingLot() {
        List<ParkingLot> parkingLots = this.parkingLots.stream().filter((pl)->{
            return pl.getCarList().size()<pl.getCapacity();
        }).collect(Collectors.toList());
        if(parkingLots.size()>0){
            return parkingLots.get(0);
        }
        return null;
    }


    public String getServiceFeedBack() {
        return serviceFeedBack;
    }

    public void setServiceFeedBack(String serviceFeedBack) {
        this.serviceFeedBack = serviceFeedBack;
    }


    public Integer getParkingBoyId() {
        return parkingBoyId;
    }

    public void setParkingBoyId(Integer parkingBoyId) {
        this.parkingBoyId = parkingBoyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingBoy)) return false;
        ParkingBoy that = (ParkingBoy) o;
        return Objects.equals(parkingBoyId, that.parkingBoyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingBoyId);
    }
}
