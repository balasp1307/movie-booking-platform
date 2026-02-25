package org.example.moviebooking.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingRequest {

    @NotBlank
    private String movieId;

    @NotBlank
    private String theatreId;

    @NotBlank
    private String theatreName;

    @NotBlank
    private String showId;

    private LocalDateTime showTime;

    @NotEmpty
    private List<String> seatNumbers;

    @Email
    @NotBlank
    private String customerEmail;

    @NotBlank
    private String city;
}