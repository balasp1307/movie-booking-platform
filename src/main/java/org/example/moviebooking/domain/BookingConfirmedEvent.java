package org.example.moviebooking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingConfirmedEvent {
    private final String message;
    private final String recipientMail;
}
