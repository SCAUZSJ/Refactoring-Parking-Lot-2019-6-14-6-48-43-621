package com.thoughtworks.tdd.ParkingLot_Refactor;

import java.util.Objects;

public class Car {
    private Integer carId;

    public Car(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(carId, car.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }
}
