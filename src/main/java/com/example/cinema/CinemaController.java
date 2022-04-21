package com.example.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {

    private final int totalRows = 9;
    private final int totalColumns = 9;
    private final List<Seat> seats = new ArrayList<>();

    {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                int maxPremiumRowNo = 4;
                int premiumPrice = 10;
                int regularPrice = 8;
                int price = i <= maxPremiumRowNo ? premiumPrice : regularPrice;
                Seat seat = new Seat(i, j, price);
                seats.add(seat);
            }
        }
    }

    private final CinemaSeats cinemaSeats = new CinemaSeats(totalRows, totalColumns, seats);

    @GetMapping("/seats")
    public CinemaSeats getCinemaRoom() {
        return cinemaSeats;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat wantedSeat) {

        if (cinemaSeats.getAvailableSeats().contains(wantedSeat)) {
            int index = cinemaSeats.getAvailableSeats().indexOf(wantedSeat);
            Seat seat = cinemaSeats.getAvailableSeats().get(index);

            if (seat.isPurchased()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "The ticket has been already purchased!");
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            } else {
                seat.setPurchased(true);
                cinemaSeats.getAvailableSeats().set(index, seat);
                return new ResponseEntity<>(seat, HttpStatus.OK);
            }

        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

}