package org.example.moviebooking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings",
        indexes = {
                @Index(name = "idx_booking_show", columnList = "showId"),
        })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String showId;
    private String theaterName;
    private LocalDateTime showTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "seat_number")
    private List<String> seatNumbers;

    private String customerEmail;
    private BigDecimal amount;
    private String bookingStatus;
    private LocalDateTime createdAt;

}