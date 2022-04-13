package com.example.cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {

    private final int totalRows = 9;
    private final int totalColumns = 9;
    private final List<Seat> seats = new ArrayList<>();

    {
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                Seat seat = new Seat(i + 1, j + 1);
                seats.add(seat);
            }
        }
    }

    private final CinemaSeats cinemaSeats = new CinemaSeats(totalRows, totalColumns, seats);

    @GetMapping("/seats")
    public CinemaSeats getCinemaRoom() {
        return cinemaSeats;
    }

}