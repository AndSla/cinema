package com.example.cinema;

import com.example.cinema.exception.SeatOutOfBoundsException;
import com.example.cinema.exception.TicketPurchasedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinemaRoom() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat wantedSeat) {

        if (cinema.getSeats().contains(wantedSeat)) {
            int index = cinema.getSeats().indexOf(wantedSeat);
            Seat seat = cinema.getSeats().get(index);

            if (seat.isPurchased()) {
                return new ResponseEntity<>(new TicketPurchasedException(), HttpStatus.BAD_REQUEST);
            } else {
                seat.setPurchased(true);
                cinema.getSeats().set(index, seat);
                return new ResponseEntity<>(seat, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(new SeatOutOfBoundsException(), HttpStatus.BAD_REQUEST);
        }
    }

}