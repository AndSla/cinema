package com.example.cinema.exception;

public class SeatOutOfBoundsException {
    private String error = "The number of a row or a column is out of bounds!";

    public SeatOutOfBoundsException() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
