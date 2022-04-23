package com.example.cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    private UUID token = UUID.randomUUID();
    private Seat seat;

    public Ticket() {
    }

    public Ticket(Seat seat) {
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
