package com.example.cinema.exception;

public class TicketPurchasedException {
    private String error = "The ticket has been already purchased!";

    public TicketPurchasedException() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
