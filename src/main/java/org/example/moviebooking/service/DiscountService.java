package org.example.moviebooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class DiscountService {

    /**
     * Applies discounts based on the number of tickets and show time.
     *
     * - If 3 or more tickets are purchased, the third ticket is 50% off.
     * - If the show time is between 12 PM and 4 PM, there's an additional 20% discount on the total.
     *
     * @param basePrice The price of a single ticket.
     * @param numberOfTickets The number of tickets being purchased.
     * @param showTime The time of the show.
     * @return The total price after applying discounts.
     */
    public BigDecimal applyDiscount(
            BigDecimal basePrice,
            int numberOfTickets,
            LocalTime showTime) {

        BigDecimal total = basePrice.multiply(BigDecimal.valueOf(numberOfTickets));

        if (numberOfTickets >= 3) {
            BigDecimal thirdTicketDiscount =
                    basePrice.multiply(BigDecimal.valueOf(0.5));
            total = total.subtract(thirdTicketDiscount);
        }

        if (showTime.isAfter(LocalTime.NOON)
                && showTime.isBefore(LocalTime.of(16, 0))) {

            BigDecimal afternoonDiscount =
                    total.multiply(BigDecimal.valueOf(0.2));
            total = total.subtract(afternoonDiscount);
        }

        return total;
    }
}