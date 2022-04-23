package com.example.cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int totalRows = 9;
    private int totalColumns = 9;
    private List<Ticket> tickets = new ArrayList<>();
    private List<Seat> seats = new ArrayList<>();

    {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                int maxPremiumRowNo = 4;
                int premiumPrice = 10;
                int regularPrice = 8;
                int price = i <= maxPremiumRowNo ? premiumPrice : regularPrice;
                Seat seat = new Seat(i, j, price);
                tickets.add(new Ticket(seat));
                seats.add(seat);
            }
        }
    }

    public Cinema() {
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @JsonIgnore
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
