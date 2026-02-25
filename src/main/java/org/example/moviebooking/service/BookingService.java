package org.example.moviebooking.service;

import org.example.moviebooking.domain.Booking;
import org.example.moviebooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository repository;

    public Booking create(Long showId, String email) {

        Booking booking = Booking.builder()
                .showId(showId)
                .userEmail(email)
                .amount(BigDecimal.valueOf(200))
                .status("PENDING_PAYMENT")
                .build();

        return repository.save(booking);
    }
}