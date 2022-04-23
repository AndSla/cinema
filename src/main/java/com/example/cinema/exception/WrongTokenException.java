package com.example.cinema.exception;

public class WrongTokenException {
    private String error = "Wrong token!";

    public WrongTokenException() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
