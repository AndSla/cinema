package com.example.cinema;

import com.example.cinema.exception.SeatOutOfBoundsException;
import com.example.cinema.exception.TicketPurchasedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinemaRoom() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat wantedSeat) {

        for (Ticket ticket : cinema.getTickets()) {

            Seat seat = ticket.getSeat();

            if (Objects.equals(seat, wantedSeat)) {
                int index = cinema.getTickets().indexOf(ticket);

                if (seat.isPurchased()) {
                    return new ResponseEntity<>(new TicketPurchasedException(), HttpStatus.BAD_REQUEST);
                } else {
                    seat.setPurchased(true);
                    ticket.setSeat(seat);
                    cinema.getTickets().set(index, ticket);
                    return new ResponseEntity<>(ticket, HttpStatus.OK);
                }

            }

        }

        return new ResponseEntity<>(new SeatOutOfBoundsException(), HttpStatus.BAD_REQUEST);

    }

}