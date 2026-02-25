package org.example.moviebooking.listener;

import lombok.RequiredArgsConstructor;
import org.example.moviebooking.client.NotificationStrategy;
import org.example.moviebooking.domain.BookingConfirmedEvent;
import org.example.moviebooking.factory.NotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingNotificationListener {

    @Autowired
    private final NotificationFactory factory;

     // This class can be used to listen for booking events and trigger notifications
    @EventListener
    public void handleBookingEvent(BookingConfirmedEvent event) {
        NotificationStrategy email = factory.getSender("EMAIL");
        email.sendNotification(event.getRecipientMail(), event.getMessage());

        NotificationStrategy whatsapp = factory.getSender("WHATSAPP");
        whatsapp.sendNotification(event.getRecipientMail(), event.getMessage());
    }
}
