package org.example.moviebooking.listener;

import org.example.moviebooking.client.NotificationStrategy;
import org.example.moviebooking.domain.BookingConfirmedEvent;
import org.example.moviebooking.factory.NotificationFactory;
import org.example.moviebooking.listener.BookingNotificationListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingNotificationListenerTest {

    @Mock
    private NotificationFactory factory;

    @Mock
    private NotificationStrategy strategy;

    @InjectMocks
    private BookingNotificationListener listener;

    @Test
    void shouldSendNotifications() {

        when(factory.getSender(anyString())).thenReturn(strategy);

        listener.handleBookingEvent(
                new BookingConfirmedEvent("test@test.com", "msg")
        );

        verify(strategy, times(2)).sendNotification(any(), any());
    }
}
