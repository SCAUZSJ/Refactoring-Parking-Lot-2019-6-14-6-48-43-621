package com.thoughtworks.tdd.ParkingLot_Refactor;

public class Ticket {
    private Integer carId;
    private Integer parkingLotId;
    private boolean state;//true 为 有效，false 为无效（已使用）
    private Integer operatorId;

    public Ticket(Integer carId) {
        this.carId = carId;
        this.state = true;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Integer parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
