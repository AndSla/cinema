package com.example.cinema.exception;

public class WrongPasswordException {
    private String error = "The password is wrong!";

    public WrongPasswordException() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
