package org.example.moviebooking.controller;

import lombok.RequiredArgsConstructor;
import org.example.moviebooking.domain.Booking;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final org.example.moviebooking.service.BookingService bookingService;

    @PostMapping
    public Booking book(@RequestParam Long showId,
                        @RequestParam String email) {

        return bookingService.create(showId, email);
    }
}
