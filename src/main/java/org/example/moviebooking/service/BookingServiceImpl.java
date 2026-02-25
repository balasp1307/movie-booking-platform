package org.example.moviebooking.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import lombok.RequiredArgsConstructor;
import org.example.moviebooking.domain.Booking;
import org.example.moviebooking.domain.BookingConfirmedEvent;
import org.example.moviebooking.model.BookingRequest;
import org.example.moviebooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    private final BookingRepository bookingRepository;

    private final HazelcastInstance hazelcastInstance;
    private final ApplicationEventPublisher applicationEventPublisher;

        @Override
        @Transactional
        public String bookTickets(BookingRequest request) {
            String lockKey = request.getShowId() + "_" + request.getSeatNumbers();
            FencedLock lock = hazelcastInstance.getCPSubsystem().getLock(lockKey);

            try {
                lock.lock();

                // TODO: Validate seat availability from inventory service

                BigDecimal totalAmount = calculatePrice(request);

                Booking booking = Booking.builder()
                        .theaterName(request.getTheatreName())
                        .showId(request.getShowId())
                        .showTime(request.getShowTime())
                        .seatNumbers(request.getSeatNumbers())
                        .customerEmail(request.getCustomerEmail())
                        .amount(totalAmount)
                        .bookingStatus("PENDING")
                        .createdAt(LocalDateTime.now())
                        .build();

                bookingRepository.save(booking);

                applicationEventPublisher.publishEvent(
                        new BookingConfirmedEvent(
                                request.getCustomerEmail(),
                                "Your booking is confirmed!"
                        )
                );

                return "Booking Successful with ID: " + booking.getId();
            } finally {
                lock.unlock();
            }
        }

        private BigDecimal calculatePrice(BookingRequest request) {

            int ticketCount = request.getSeatNumbers().size();

            // TODO: Base price should ideally come from a configuration or database, hardcoding for simplicity
            BigDecimal basePrice = BigDecimal.valueOf(200.0);

            BigDecimal total = basePrice.multiply(BigDecimal.valueOf(ticketCount));

            if (ticketCount >= 3) {
                BigDecimal thirdTicketDiscount =
                        basePrice.multiply(BigDecimal.valueOf(0.5));
                total = total.subtract(thirdTicketDiscount);
            }

            if (request.getShowTime().getHour() >= 12 && request.getShowTime().getHour() < 16) {
                BigDecimal afternoonDiscount =
                        total.multiply(BigDecimal.valueOf(0.2));
                total = total.subtract(afternoonDiscount);
            }

            return total;
        }
}