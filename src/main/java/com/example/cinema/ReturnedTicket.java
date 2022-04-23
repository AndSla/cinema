package com.example.cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnedTicket {

    private Seat returnedTicket;

    public ReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    @JsonProperty("returned_ticket")
    public Seat getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

}
