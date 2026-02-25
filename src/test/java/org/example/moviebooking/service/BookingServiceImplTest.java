package org.example.moviebooking.service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.CPSubsystem;
import com.hazelcast.cp.lock.FencedLock;
import org.example.moviebooking.domain.Booking;
import org.example.moviebooking.domain.BookingConfirmedEvent;
import org.example.moviebooking.model.BookingRequest;
import org.example.moviebooking.repository.BookingRepository;
import org.example.moviebooking.service.BookingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HazelcastInstance hazelcastInstance;

    @Mock
    private FencedLock lock;

    @Mock
    private BookingConfirmedEvent bookingConfirmedEvent;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingRequest request;

    @BeforeEach
    void setup() {
        request = new BookingRequest();
        request.setMovieId("MOV1");
        request.setTheatreId("T1");
        request.setTheatreName("PVR");
        request.setShowId("SHOW1");
        request.setSeatNumbers(List.of("A1", "A2", "A3"));
        request.setShowTime(LocalDateTime.now().withHour(14));
        request.setCustomerEmail("test@test.com");
        request.setCity("Bangalore");
    }

    @Test
    public void bookTickets_happyPath() {


        CPSubsystem cpSubsystem = mock(CPSubsystem.class);
        when(hazelcastInstance.getCPSubsystem()).thenReturn(cpSubsystem);
        when(hazelcastInstance.getCPSubsystem().getLock(anyString())).thenReturn(lock);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
            Booking booking = invocation.getArgument(0);
            booking.setId(Long.valueOf(1L));
            return booking;
        });

        String result = bookingService.bookTickets(request);

        assertThat(result).contains("Booking Successful");

        verify(lock).lock();
        verify(lock).unlock();
        verify(bookingRepository).save(any());
        verify(publisher).publishEvent(any(BookingConfirmedEvent.class));
    }

    @Test
    public void bookTickets_whenLockFails_shouldThrow() {

        CPSubsystem cpSubsystem = mock(CPSubsystem.class);
        when(hazelcastInstance.getCPSubsystem()).thenReturn(cpSubsystem);
        when(hazelcastInstance.getCPSubsystem().getLock(any())).thenThrow(new RuntimeException("Lock error"));

        assertThatThrownBy(() -> bookingService.bookTickets(request))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void calculatePrice_shouldApplyDiscounts() {

        CPSubsystem cpSubsystem = mock(CPSubsystem.class);
        when(hazelcastInstance.getCPSubsystem()).thenReturn(cpSubsystem);
        when(hazelcastInstance.getCPSubsystem().getLock(any())).thenReturn(lock);
        when(bookingRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        bookingService.bookTickets(request);

        ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);
        verify(bookingRepository).save(captor.capture());

        Booking saved = captor.getValue();

        assertThat(saved.getAmount()).isLessThan(BigDecimal.valueOf(600.0));
    }
}
