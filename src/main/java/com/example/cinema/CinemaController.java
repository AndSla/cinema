package com.example.cinema;

import com.example.cinema.exception.SeatOutOfBoundsException;
import com.example.cinema.exception.TicketPurchasedException;
import com.example.cinema.exception.WrongPasswordException;
import com.example.cinema.exception.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
public class CinemaController {

    private final Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getCinemaRoom() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat wantedSeat) {

        for (Ticket ticket : cinema.getTickets()) {

            Seat seat = ticket.getSeat();

            if (Objects.equals(seat, wantedSeat)) {
                int index = cinema.getTickets().indexOf(ticket);

                if (seat.isPurchased()) {
                    return new ResponseEntity<>(new TicketPurchasedException(), HttpStatus.BAD_REQUEST);
                } else {
                    seat.setPurchased(true);
                    ticket.setSeat(seat);
                    ticket.setToken(UUID.randomUUID());
                    cinema.getTickets().set(index, ticket);
                    return new ResponseEntity<>(ticket, HttpStatus.OK);
                }

            }

        }

        return new ResponseEntity<>(new SeatOutOfBoundsException(), HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Ticket ticketToReturn) {
        if (cinema.getTickets().contains(ticketToReturn)) {
            int index = cinema.getTickets().indexOf(ticketToReturn);
            Ticket ticket = cinema.getTickets().get(index);
            ticket.setToken(null);
            ticket.getSeat().setPurchased(false);
            cinema.getTickets().set(index, ticket);
            ReturnedTicket returnedTicket = new ReturnedTicket(ticket.getSeat());
            return new ResponseEntity<>(returnedTicket, HttpStatus.OK);
        }
        return new ResponseEntity<>(new WrongTokenException(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(defaultValue = "") String password) {
        String expectedPassword = "super_secret";
        int currentIncome = 0;
        int numberOfAvailableSeats = cinema.getSeats().size();
        int numberOfPurchasedTickets = 0;

        if (expectedPassword.equals(password)) {

            for (Seat seat : cinema.getSeats()) {
                if (seat.isPurchased()) {
                    currentIncome += seat.getPrice();
                    numberOfAvailableSeats -= 1;
                    numberOfPurchasedTickets += 1;
                }
            }

            Stats stats = new Stats(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets);

            return new ResponseEntity<>(stats, HttpStatus.OK);

        }

        return new ResponseEntity<>(new WrongPasswordException(), HttpStatus.UNAUTHORIZED);

    }

}