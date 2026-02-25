package org.example.moviebooking.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.moviebooking.model.BookingRequest;
import org.example.moviebooking.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public String book(@Valid @RequestBody BookingRequest request) {

        return bookingService.bookTickets(request);
    }
}
