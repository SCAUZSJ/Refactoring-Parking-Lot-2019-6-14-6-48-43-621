package com.thoughtworks.tdd.ParkingLot_Refactor.Enum;

public enum FeedBack {

    PleaseProvide("Please provide your parking ticket."),
    UnrecognizedTicket("Unrecognized parking ticket."),
    NotEnoughPosition("Not enough position.");
    private String message;

    FeedBack(String message){
        this.message = message;

    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
